package com.example.gupo_android.test;


import android.support.test.espresso.PerformException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.espresso.util.HumanReadables;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gupo_android.test.Adapter.RecyclerviewAdapter;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Objects;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction button = onView(
                allOf(withId(R.id.button5), withText("RecyclerView测试"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        button.perform(click());

        onView(allOf(withId(R.id.mRecyclerView), isDisplayed()));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*ViewInteraction view = onView(allOf(ViewMatchers.withId(R.id.mRecyclerView), isDisplayed()));
        Log.i("whx","view:成功");
        view.perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));*/


        ViewInteraction view1 = onView(allOf(withId(R.id.myRV), isDisplayed()));
        Log.i("whx", "view1:成功");
        view1.perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));


        onView(ViewMatchers.withId(R.id.myRV))
                .perform(RecyclerViewActions.scrollToHolder(isInTheMiddle()));

        ViewInteraction view2 = onView(allOf(withId(R.id.myRV), isDisplayed()));

        view2.perform(onItemAction(0, R.id.mRecyclerView, 2, click()));
        view2.perform(onItemAction(2, R.id.mRecyclerView, 3, click()));


    }


    /**
     * Matches the {@link RecyclerviewAdapter.ViewHolder}s in the middle of the list.
     */
    private static Matcher<RecyclerviewAdapter.ViewHolder> isInTheMiddle() {
        return new TypeSafeMatcher<RecyclerviewAdapter.ViewHolder>() {
            @Override
            protected boolean matchesSafely(RecyclerviewAdapter.ViewHolder customHolder) {
                return customHolder.getIsInTheMiddle();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("item in the middle");
            }
        };
    }


    /**
     * @param title 文本:列表中的文本
     * @return Matcher
     */
    public static Matcher<RecyclerView.ViewHolder> withTitle(final String title) {
        return new BoundedMatcher<RecyclerView.ViewHolder, RecyclerviewAdapter.ViewHolder>(RecyclerviewAdapter.ViewHolder.class) {
            @Override
            protected boolean matchesSafely(RecyclerviewAdapter.ViewHolder item) {
                return item.getName().getText().toString().equals(title);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("view holder with title: " + title);
            }
        };
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    /**
     * 列子
     *
     * @param expected 文本:对比的文本
     * @return Matcher
     */
    public static Matcher<View> withTaskViewName(final String expected) {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                if (item != null && item.findViewById(R.id.tvName) != null) {
                    TextView taskName = item.findViewById(R.id.tvName);
                    return !TextUtils.isEmpty(taskName.getText()) && taskName.getText().toString().equals(expected);
                } else {
                    return false;
                }
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Looked for " + expected + " in the task_item_layout.xml file");
            }
        };
    }

    /**
     * 根据下标找寻view是否存在
     *
     * @param index index
     * @return Matcher
     */
    public static Matcher<View> withTaskViewIndex(final int position, final int index) {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                if (item != null) {
                    LinearLayout linearLayout = (LinearLayout) item;
                    RecyclerView recyclerView = (RecyclerView) linearLayout.getChildAt(1);
                    if (recyclerView != null && recyclerView.getVisibility() == View.VISIBLE) {
                        View view1 = recyclerView.getChildAt(index);
                        return view1 != null;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("寻找下标为:" + index + "的view");
            }
        };
    }

    /**
     * @param position          第一个下标
     * @param recyclerViewId    子项中 recyclerView
     * @param recyclerViewIndex 子项的下标 recyclerView
     * @param viewAction        操作
     * @return ViewAction
     */
    private static ViewAction onItemAction(final int position, final int recyclerViewId, final int recyclerViewIndex, final ViewAction viewAction) {
        return new ViewAction() {

            @Override
            public Matcher<View> getConstraints() {
                return allOf(isAssignableFrom(RecyclerView.class), isDisplayed());
            }

            @Override
            public String getDescription() {
                return "actionOnItemAtPosition performing ViewAction: "
                        + viewAction.getDescription()
                        + " on item at position: "
                        + position;
            }

            @Override
            public void perform(UiController uiController, View view) {
                RecyclerView recyclerView = (RecyclerView) view;
                scrollToPositionViewAction(position).perform(uiController, view);
                uiController.loopMainThreadUntilIdle();
                RecyclerView.ViewHolder viewHolderForPosition = recyclerView.findViewHolderForLayoutPosition(position);
                if (null == viewHolderForPosition) {
                    throw new PerformException.Builder()
                            .withActionDescription(this.toString())
                            .withViewDescription(HumanReadables.describe(view))
                            .withCause(new IllegalStateException("下标不存在: " + position))
                            .build();
                } else {
                    //View viewAtPosition = viewHolderForPosition.itemView;
                    LinearLayout viewAtPosition = (LinearLayout) viewHolderForPosition.itemView;
//                    TextView t = (TextView) viewAtPosition.getChildAt(0);
//                    if (t != null) {
//                        Log.i("whx", "name:" + t.getText().toString());
//                        try {
//                            TextView view1 = viewAtPosition.findViewById(R.id.tvName);
//                            if (view1 != null) {
//                                Log.i("whx", "view1:" + view1.getText().toString());
//                            }
//                        } catch (Exception e) {
//                            Log.i("whx", "找寻错误");
//                            e.printStackTrace();
//                        }
//                    }
                    RecyclerView recyclerView1 = viewAtPosition.findViewById(recyclerViewId);
                    if (recyclerView1 == null) {
                        throw new PerformException.Builder()
                                .withActionDescription(this.toString())
                                .withViewDescription(HumanReadables.describe(viewAtPosition))
                                .withCause(new IllegalStateException("子项中的RecyclerView没有找到"))
                                .build();
                    } else {
                        if (recyclerView1.getVisibility() == View.VISIBLE) {
                            RecyclerView.ViewHolder viewHolder = recyclerView1.findViewHolderForLayoutPosition(recyclerViewIndex);
                            if (viewHolder == null) {
                                throw new PerformException.Builder()
                                        .withActionDescription(this.toString())
                                        .withViewDescription(HumanReadables.describe(viewAtPosition))
                                        .withCause(new IllegalStateException("子项中的RecyclerView.ViewHolder没有找到"))
                                        .build();
                            } else {
                                // viewAction.perform(uiController, viewAtPosition);
                                viewAction.perform(uiController, viewHolder.itemView);
                            }
                        } else {
                            throw new PerformException.Builder()
                                    .withActionDescription(this.toString())
                                    .withViewDescription(HumanReadables.describe(viewAtPosition))
                                    .withCause(new IllegalStateException("子项RecyclerView未显示"))
                                    .build();
                        }
                    }


                }


            }
        };
    }


    /**
     * {@link ViewAction} which scrolls {@link RecyclerView} to a given position. See {@link
     * RecyclerViewActions#scrollToPosition(int)} for more details.
     */
    private static ViewAction scrollToPositionViewAction(final int position) {
        return new ViewAction() {
            @SuppressWarnings("unchecked")
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isAssignableFrom(RecyclerView.class), isDisplayed());
            }

            @Override
            public String getDescription() {
                return "scroll RecyclerView to position: " + position;
            }

            @Override
            public void perform(UiController uiController, View view) {
                RecyclerView recyclerView = (RecyclerView) view;
                recyclerView.scrollToPosition(position);
                uiController.loopMainThreadUntilIdle();
            }
        };
    }

    /**
     *  https://developer.android.com/training/testing/espresso/recipes
     * onView(withId(R.id.list)).check(matches(not(withAdaptedData(withItemContent("item: 168")))));
     *
     * @param dataMatcher Matcher
     * @return Matcher
     */
    private static Matcher<View> withAdaptedData(final Matcher<Object> dataMatcher) {
        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("with class name: ");
                dataMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof AdapterView)) {
                    return false;
                }

                @SuppressWarnings("rawtypes")
                Adapter adapter = ((AdapterView) view).getAdapter();
                for (int i = 0; i < adapter.getCount(); i++) {
                    if (dataMatcher.matches(adapter.getItem(i))) {
                        return true;
                    }
                }

                return false;
            }
        };
    }

}
