package com.example.onlinenurserystore;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class LoginInstrumentedTest {

    @Rule
    public ActivityTestRule <LoginActivity> testRule = new ActivityTestRule<>(LoginActivity.class);

    private String Email = "d";
    private String Password = "d";

    @Test
    public void LoginUITest() {
        onView(withId(R.id.emailLF))
                .perform(typeText(Email));

        closeSoftKeyboard();

        onView(withId(R.id.passwordLF))
                .perform(typeText(Password));

        closeSoftKeyboard();

        onView(withId(R.id.signinLF)).perform(click());
    }
}
