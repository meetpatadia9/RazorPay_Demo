package com.ipsmeet.razorpaydemo

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ipsmeet.razorpaydemo.databinding.ActivityMainBinding
import com.razorpay.Checkout
import com.razorpay.ExternalWalletListener
import com.razorpay.PaymentData
import com.razorpay.PaymentResultListener
import com.razorpay.PaymentResultWithDataListener
import org.json.JSONObject
import java.util.Objects

class MainActivity : AppCompatActivity(), PaymentResultWithDataListener, ExternalWalletListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
        *  To ensure faster loading of the Checkout form,
        *  call this method as early as possible in your checkout flow
        * */
        Checkout.preload(applicationContext)

    /*    checkout.setKeyID(getString(R.string.key_id))
        checkout.setImage(R.mipmap.ic_launcher)
*/
        binding.btnPayViaRazorPay.setOnClickListener {
            startPayment()
        }
    }

    private fun startPayment() {
        val checkout = Checkout()
        checkout.setKeyID(getString(R.string.key_id))

        val activity:Activity = this

        try {
            val orderReqObj = JSONObject()
            orderReqObj.put("name", getString(R.string.app_name))
            orderReqObj.put("description", "Testing")
            orderReqObj.put("allow_rotation", false)

            orderReqObj.put("currency", "INR")
            orderReqObj.put("amount", "100")
            orderReqObj.put("send_sms_hash", true)

           /* val retryObj = JSONObject()
            retryObj.put("enabled", true)
            retryObj.put("max_count", 4)
            orderReqObj.put("retry", retryObj)*/

            val prefillObj = JSONObject()
            prefillObj.put("email", "testing.razorpay@example.com")
            prefillObj.put("contact", "8734963629")
            orderReqObj.put("prefill", prefillObj)

            /*val methodsObj = JSONObject()
            methodsObj.put("upi" ,"1")

            val checkoutJSON = JSONObject()
            checkoutJSON.put("method", methodsObj)

            val optionsObj = JSONObject()
            optionsObj.put("checkout", checkoutJSON)
            orderReqObj.put("options", optionsObj)*/

            checkout.open(activity, orderReqObj)
        } catch (e: Exception) {
            Toast.makeText(this, "Error in payment: " + e.message, Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    override fun onPaymentSuccess(p0: String?, p1: PaymentData?) {
        Toast.makeText(this, "Payment Success: $p0", Toast.LENGTH_SHORT).show()
    }

    override fun onPaymentError(p0: Int, p1: String?, p2: PaymentData?) {
        Toast.makeText(this, "Payment Failed: $p1", Toast.LENGTH_SHORT).show()
        Log.d("onPaymentError: ", "p0: $p0")
        Log.d("onPaymentError: ", "p1: $p1")
    }

    override fun onExternalWalletSelected(p0: String?, p1: PaymentData?) {

    }
}