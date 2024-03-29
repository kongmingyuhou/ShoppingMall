package com.example.shoppingmall

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login_forget.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast


class LoginForgetActivity : AppCompatActivity() {

    private var mVerifyCode:String?=null
    private var mPhone:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_forget)

        mPhone=intent.getStringExtra("phone")
    }

    private fun getVerifycode(){
        if(mPhone == null || mPhone!!.length<11){
            toast("请输入正确的手机号")
            return
        }
        mVerifyCode=String.format("%06d", (Math.random() * 1000000 % 1000000).toInt())
        alert("手机号$mPhone，本次验证码是$mVerifyCode，请输入验证码", "请记住验证码"){
            positiveButton("OK"){}
        }.show()
    }

    private fun doConfirm(){
        val password_first=et_password_first.text.toString()
        val password_second=et_password_second.text.toString()
        if (password_first.isBlank() || password_first.length<6){
            toast("请输入正确的新密码")
            return
        }else if (password_first != password_second){
            toast("两次输入的新密码不一致,请重新输入")
            return
        }else{
            toast("密码修改成功")
            //携带修改后的新密码返回主登录页面
            val intent=Intent().putExtra("new_password",password_first)
            setResult(Activity.RESULT_OK,intent)
            finish()
        }
    }









}
