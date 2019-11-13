package com.example.android.architecture.blueprints.todoapp.taskdetail

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.ServiceLocator
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.FakeTaskRepository
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@MediumTest
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class TaskDetailFragmentTest {

    lateinit var tasksRepository: TasksRepository

    @Before
    fun setUp() {
        tasksRepository = FakeTaskRepository()
        ServiceLocator.tasksRepository = tasksRepository
    }

    @After
    fun tearDown() {
        runBlockingTest {
            ServiceLocator.resetRepository()
        }
    }

    @Test
    fun activeTaskDetails_DisplayedInUi() = runBlockingTest {
        val activeTask = Task("Active Task", "AndroidX rocks", false)
        tasksRepository.saveTask(activeTask)
        val bundle = TaskDetailFragmentArgs(activeTask.id).toBundle()
        launchFragmentInContainer<TaskDetailFragment>(bundle, R.style.AppTheme)
        sleep(2000)
    }

}