package com.test.hellokotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.test.hellokotlin.bean.User
import com.test.hellokotlin.repository.Repository

class LiveDataModel(countReserved: Int) : ViewModel() {
    val counter: LiveData<Int>
        get() = _counter
    private val _counter = MutableLiveData<Int>()

 /**-------------------------------------------------------------------------**/

    /**
     * 将userLiveData 声明成了private以保证数据的封装性，外部使用的时候
     * 只需要观察usernName这个liveData就好了
     *
     */
    private val userLiveData = MutableLiveData<User>()
    val userName: LiveData<String> = Transformations.map(userLiveData) { user ->
        "${user.firstName}${user.lastName}"

    }

    fun  getUser(userId:String):LiveData<User>{
        return Repository.getUser(userId)

    }
    /**------------------------------------------------------------------------------------**/
    private val  userIdLiveData= MutableLiveData<String>()

    val  user:LiveData<User> =Transformations.switchMap(userIdLiveData){ userId->
        Repository.getUser(userId)

    }

    fun  getUser1(userId: String){
        userIdLiveData.value =userId
    }

    /**------------------------------------------------------------------------------------**/
    init {
        _counter.value = countReserved
    }

    fun plusOne() {
        val count = _counter.value ?: 0
        _counter.value = count + 1
    }

    fun clear() {
        _counter.value = 0
    }
}