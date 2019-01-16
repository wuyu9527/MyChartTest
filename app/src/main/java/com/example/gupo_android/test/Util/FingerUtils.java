package com.example.gupo_android.test.Util;


import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.biometrics.BiometricPrompt;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;
import android.util.Log;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.util.concurrent.Executor;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;


/**
 * @author : whx
 * @date : 2018/10/30 16:27
 */
public class FingerUtils {

    private static final String KEY_NAME = "BiometricPromptApi";

    //访问指纹硬件的类
    private FingerprintManager manager;
    //生物密码
    private BiometricPrompt biometricPrompt;
    //指纹监听
    private CancellationSignal signal;
    //此类基于Java加密API的一个包装类,用于防止在指纹扫描中被第三方恶意攻击
    private FingerprintManager.CryptoObject cryptoObject;
    //数字签名
    private Signature mSignature;
    //加密钥匙
    private String mToBeSignedMessage;

    private static volatile FingerUtils instance;

    private Activity activity;

    private Context context;
    //自定义指纹监听
    private OnFingerSucceededListener listener;

    public interface OnFingerSucceededListener {
        void onSucceed();

        void onError(int code, String msg);

        void onFailed(int failedNum);
    }

    /**
     * 单列 去除重复建立
     *
     * @return FingerUtils
     */
    public static FingerUtils instance() {
        FingerUtils f = instance;
        if (instance == null) {
            Class var1 = FingerUtils.class;
            synchronized (FingerUtils.class) {
                f = instance;
                if (instance == null) {
                    f = new FingerUtils();
                    instance = f;
                }
            }
        }
        return f;
    }

    /**
     * 初始化
     *
     * @param context c
     */
    public void init(Context context) {
        this.context = context;
        if (Build.VERSION.SDK_INT >= 28) {
            try {
                biometricPrompt = new BiometricPrompt.Builder(context)
                        .setTitle("指纹验证")
                        .setSubtitle("小标题")
                        .setNegativeButton("取消", context.getMainExecutor(), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.i("whx", "dialog:" + dialog + ":" + which);
                            }
                        })
                        .setDescription("描述")
                        .build();
                KeyPair keyPair = generateKeyPair(KEY_NAME, true);
                // Send public key part of key pair to the server, this public key will be used for authentication
                //将密钥对的公钥部分发送到服务器，此公钥将用于身份验证
                mToBeSignedMessage = Base64.encodeToString(keyPair.getPublic().getEncoded(), Base64.URL_SAFE) +
                        ":" +
                        KEY_NAME +
                        ":" +
                        // Generated by the server to protect against replay attack
                        //由服务器生成以防止重播攻击
                        "12345";

                mSignature = initSignature(KEY_NAME);
            } catch (Exception e) {
               e.printStackTrace();
            }
        } else if (Build.VERSION.SDK_INT >= 23) {
            manager = (FingerprintManager) context.getSystemService(Context.FINGERPRINT_SERVICE);
        }
    }

    /**
     * 取消指纹监听
     */
    public void cancel() {
        if (signal != null) {
            signal.cancel();
            signal = null;
        }
    }


    /**
     * 判断指纹是否可用
     *
     * @return Boolean
     */
    @TargetApi(Build.VERSION_CODES.M)
    public boolean isFingerprint() {
        if (Build.VERSION.SDK_INT >= 23) {
            //此方法为了保证判断是否支持支持指纹不报错
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                //没有指纹解锁的权限
                return false;
            }
            //硬件设备是否支持指纹解锁功能
            if (!manager.isHardwareDetected()) {
                //该手机不支持指纹解锁
                return false;
            }
            //判断是否录入指纹
            if (!manager.hasEnrolledFingerprints()) {
                //没有录入指纹
                return false;
            }
            return true;
        }
        return false;
    }


    /**
     * 开始指纹识别
     */
    public void startListener(final OnFingerSucceededListener listener) {
        this.listener = listener;

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            listener.onError(-1, "指纹权限未开启");
            return;
        } else if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_BIOMETRIC) != PackageManager.PERMISSION_GRANTED) {
            listener.onError(-1, "指纹权限未开启");
            return;
        }

        if (signal == null) {
            signal = new CancellationSignal();
            signal.setOnCancelListener(new CancellationSignal.OnCancelListener() {
                @Override
                public void onCancel() {
                    Log.i("whx","取消指纹监听");
                }
            });
        }
        Log.i("whx","指纹监听:"+signal.isCanceled());

        //此类基于Java加密API的一个包装类,用于防止在指纹扫描中被第三方恶意攻击
        if (Build.VERSION.SDK_INT >= 28) {

            biometricPrompt.authenticate(new BiometricPrompt.CryptoObject(mSignature),
                    signal, context.getMainExecutor(), new BiometricPrompt.AuthenticationCallback() {
                        @Override
                        public void onAuthenticationError(int errorCode, CharSequence errString) {
                            super.onAuthenticationError(errorCode, errString);
                            Log.i("whx","onAuthenticationError");
                            listener.onError(errorCode,errString.toString());
                            cancel();
                        }

                        @Override
                        public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                            super.onAuthenticationHelp(helpCode, helpString);
                            Log.i("whx","onAuthenticationHelp");
                            listener.onError(helpCode,helpString.toString());
                        }

                        @Override
                        public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                            super.onAuthenticationSucceeded(result);
                            listener.onSucceed();
                            cancel();
                        }

                        @Override
                        public void onAuthenticationFailed() {
                            super.onAuthenticationFailed();
                            listener.onFailed(1);
                        }
                    });
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            FingerprintManager.AuthenticationCallback callBack = new FingerprintManager.AuthenticationCallback() {


                @Override
                public void onAuthenticationError(int errorCode, CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString);
                    listener.onError(errorCode,errString.toString());
                    cancel();
                }

                @Override
                public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                    super.onAuthenticationHelp(helpCode, helpString);
                    listener.onError(helpCode,helpString.toString());
                }

                //指纹识别成功
                @Override
                public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);
                    listener.onSucceed();
                    cancel();
                }

                //指纹识别失败
                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                    listener.onFailed(1);
                }
            };
            CryptoObjectHelper cryptoObjectHelper = null;
            try {
                cryptoObjectHelper = new CryptoObjectHelper();
                manager.authenticate(cryptoObjectHelper.buildCryptoObject(), signal, 0, callBack, null);
            } catch (Exception e) {
                if (cryptoObjectHelper == null) {
                    manager.authenticate(null, signal, 0, callBack, null);
                }
                e.printStackTrace();
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    public class CryptoObjectHelper {
        // This can be key name you want. Should be unique for the app.
        private static final String KEY_NAME = "com.nestia.android.uikit.biometric.CryptoObjectHelper";

        // We always use this keystore on Android.
        private static final String KEYSTORE_NAME = "AndroidKeyStore";

        // Should be no need to change these values.
        private static final String KEY_ALGORITHM = KeyProperties.KEY_ALGORITHM_AES;
        private static final String BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC;
        private static final String ENCRYPTION_PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7;
        private static final String TRANSFORMATION = KEY_ALGORITHM + "/" +
                BLOCK_MODE + "/" +
                ENCRYPTION_PADDING;
        private final KeyStore _keystore;

        CryptoObjectHelper() throws Exception {
            _keystore = KeyStore.getInstance(KEYSTORE_NAME);
            _keystore.load(null);
        }

        public FingerprintManager.CryptoObject buildCryptoObject() throws Exception {
            Cipher cipher = createCipher(true);
            return new FingerprintManager.CryptoObject(cipher);
        }

        private Cipher createCipher(boolean retry) throws Exception {
            Key key = GetKey();
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            try {
                cipher.init(Cipher.ENCRYPT_MODE | Cipher.DECRYPT_MODE, key);
            } catch (KeyPermanentlyInvalidatedException e) {
                _keystore.deleteEntry(KEY_NAME);
                if (retry) {
                    createCipher(false);
                } else {
                    throw new Exception("Could not create the cipher for fingerprint authentication.", e);
                }
            }
            return cipher;
        }

        private Key GetKey() throws Exception {
            Key secretKey;
            if (!_keystore.isKeyEntry(KEY_NAME)) {
                CreateKey();
            }

            secretKey = _keystore.getKey(KEY_NAME, null);
            return secretKey;
        }

        private void CreateKey() throws Exception {
            KeyGenerator keyGen = KeyGenerator.getInstance(KEY_ALGORITHM, KEYSTORE_NAME);
            KeyGenParameterSpec keyGenSpec =
                    new KeyGenParameterSpec.Builder(KEY_NAME, KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                            .setBlockModes(BLOCK_MODE)
                            .setEncryptionPaddings(ENCRYPTION_PADDING)
                            .setUserAuthenticationRequired(true)
                            .build();
            keyGen.init(keyGenSpec);
            keyGen.generateKey();
        }
    }


    /**
     * Generate NIST P-256 EC Key pair for signing and verification
     *
     * @param keyName keyName
     * @param invalidatedByBiometricEnrollment 生物统计登记无效
     * @return {@link KeyPair}
     */
    @RequiresApi(api = Build.VERSION_CODES.P)
    private KeyPair generateKeyPair(String keyName, boolean invalidatedByBiometricEnrollment) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_EC, "AndroidKeyStore");

        KeyGenParameterSpec.Builder builder = new KeyGenParameterSpec.Builder(keyName,
                KeyProperties.PURPOSE_SIGN)
                .setAlgorithmParameterSpec(new ECGenParameterSpec("secp256r1"))
                .setDigests(KeyProperties.DIGEST_SHA256,
                        KeyProperties.DIGEST_SHA384,
                        KeyProperties.DIGEST_SHA512)
                // Require the user to authenticate with a biometric to authorize every use of the key
                .setUserAuthenticationRequired(true)
                .setInvalidatedByBiometricEnrollment(invalidatedByBiometricEnrollment);

        keyPairGenerator.initialize(builder.build());

        return keyPairGenerator.generateKeyPair();
    }

    @Nullable
    private KeyPair getKeyPair(String keyName) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        if (keyStore.containsAlias(keyName)) {
            // Get public key
            PublicKey publicKey = keyStore.getCertificate(keyName).getPublicKey();
            // Get private key
            PrivateKey privateKey = (PrivateKey) keyStore.getKey(keyName, null);
            // Return a key pair
            return new KeyPair(publicKey, privateKey);
        }
        return null;
    }


    @Nullable
    private Signature initSignature(String keyName) throws Exception {
        KeyPair keyPair = getKeyPair(keyName);

        if (keyPair != null) {
            Signature signature = Signature.getInstance("SHA256withECDSA");
            signature.initSign(keyPair.getPrivate());
            return signature;
        }
        return null;
    }

}
