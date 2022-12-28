package com.teamnk.kimiljung.api

import com.teamnk.kimiljung.feature.changepassword.ChangePasswordAPI
import com.teamnk.kimiljung.feature.enterbirthday.EnterBirthdayAPI
import com.teamnk.kimiljung.feature.login.LoginAPI
import com.teamnk.kimiljung.feature.mypage.MyPageAPI
import com.teamnk.kimiljung.feature.post.PostAPI
import com.teamnk.kimiljung.feature.register.RegisterAPI

val loginAPIProvider: LoginAPI by lazy {
    RetrofitClient.getRetrofit()!!.create(LoginAPI::class.java)
}

val registerAPIProvider: RegisterAPI by lazy {
    RetrofitClient.getRetrofit()!!.create(RegisterAPI::class.java)
}

val myPageAPIProvider: MyPageAPI by lazy {
    RetrofitClient.getRetrofit()!!.create(MyPageAPI::class.java)
}

val changePasswordAPIProvider: ChangePasswordAPI by lazy {
    RetrofitClient.getRetrofit()!!.create(ChangePasswordAPI::class.java)
}

val enterBirthdayAPIProvider: EnterBirthdayAPI by lazy {
    RetrofitClient.getRetrofit()!!.create(EnterBirthdayAPI::class.java)
}

val postAPIProvider : PostAPI by lazy {
    RetrofitClient.getRetrofit()!!.create(PostAPI::class.java)
}