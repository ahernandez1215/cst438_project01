package com.daclink.drew.sp22.cst438_project01_starter;
import android.content.Context;

import androidx.lifecycle.ViewModelProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;
import com.daclink.drew.sp22.cst438_project01_starter.db.AppRepository;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserDAO;
import com.daclink.drew.sp22.cst438_project01_starter.db.entities.User;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)

public class UserDAO_Test {

    @Test
    public void userInsertTest() throws ExecutionException, InterruptedException {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        UserDAO testDB = AppDatabase.getInstance(appContext).getUserDAO();

        User user = new User("admin", "admin", "admin", false);

        AppDatabase.databaseWriteExecutor.execute(() -> {
            testDB.insert(user);
        });

        User user2 = new User("wrong","user", "bad", false);
        Future<User> userFuture = AppDatabase.databaseWriteExecutor.submit(new Callable<User>() {
            @Override
            public User call() throws Exception {
                return testDB.getUserByUsername(user.getUsername());
            }
        });

        try {
            user2 = userFuture.get();
        } catch (Exception e) {
        }

        assertEquals(user, user2);
    }

    @Test
    public void userUpdateTest() throws ExecutionException, InterruptedException {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        UserDAO testDB = AppDatabase.getInstance(appContext).getUserDAO();

        User user = new User("admin", "admin", "admin", false);

        User finalUser2 = user;
        AppDatabase.databaseWriteExecutor.execute(() -> {
            testDB.insert(finalUser2);
        });

        User updatedUser = new User("updatedAdmin", "updatedAdmin", "admin", false);

        User finalUser = user;
        Future<User> userFuture = AppDatabase.databaseWriteExecutor.submit(new Callable<User>() {
            @Override
            public User call() throws Exception {
                return testDB.getUserByUsername(finalUser.getUsername());
            }
        });

        try {
            user = userFuture.get();
        } catch (Exception e) {
        }

        updatedUser.setUserId(user.getUserId());

        AppDatabase.databaseWriteExecutor.submit(() -> {
            testDB.update(updatedUser);
        });

        //Now get user from database
        User finalUser1 = user;
        Future<User> userUpdatedFuture = AppDatabase.databaseWriteExecutor.submit(new Callable<User>() {
            @Override
            public User call() throws Exception {
                return testDB.getUserByUserId(finalUser1.getUserId());
            }
        });

        try {
            user = userUpdatedFuture.get();
        } catch (Exception e) {
        }

        assertEquals(updatedUser, user);
    }

    @Test
    public void userDeleteTest() throws ExecutionException, InterruptedException {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        UserDAO testDB = AppDatabase.getInstance(appContext).getUserDAO();

        User user = new User("admin", "admin", "admin", false);

        AppDatabase.databaseWriteExecutor.execute(() -> {
            testDB.insert(user);
        });

        // Delete the existing user
        AppDatabase.databaseWriteExecutor.execute(() ->{
            testDB.delete(user);
        });

        //Now that existing user was deleted test to see if it still exists
        User deletedUser = null;
        Future<User> userDeletedFuture = AppDatabase.databaseWriteExecutor.submit(new Callable<User>() {
            @Override
            public User call() throws Exception {
                return testDB.getUserByUserId(user.getUserId());
            }
        });

        try {
            deletedUser = userDeletedFuture.get();
        } catch (Exception e) {
        }
        assertNull(deletedUser);
    }
}
