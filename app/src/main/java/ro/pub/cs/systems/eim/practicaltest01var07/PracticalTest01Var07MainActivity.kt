package ro.pub.cs.systems.eim.practicaltest01var07

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class PracticalTest01Var07MainActivity : AppCompatActivity() {

    companion object {
        private const val KEY_LAST_RESULT = "lastResult"
        private const val KEY_LAST_OPERATION = "lastOperation"
    }

    private lateinit var row1LeftEditText: EditText
    private lateinit var row1RightEditText: EditText
    private lateinit var row2LeftEditText: EditText
    private lateinit var row2RightEditText: EditText

    private var lastResult: Double = 0.0
    private var lastOperation: String = ""

    private val secondaryActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let { data ->
                lastResult = data.getDoubleExtra("result", 0.0)
                lastOperation = data.getStringExtra("operation") ?: ""
                val message = "Received $lastOperation: $lastResult"
                Log.d("PracticalTest01Var07", message)
            }
        }
    }

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == PracticalTest01Var07Service.ACTION_UPDATE_VALUES) {
                val value1 = intent.getIntExtra(PracticalTest01Var07Service.EXTRA_VALUE_1, 0)
                val value2 = intent.getIntExtra(PracticalTest01Var07Service.EXTRA_VALUE_2, 0)
                val value3 = intent.getIntExtra(PracticalTest01Var07Service.EXTRA_VALUE_3, 0)
                val value4 = intent.getIntExtra(PracticalTest01Var07Service.EXTRA_VALUE_4, 0)

                row1LeftEditText.setText(value1.toString())
                row1RightEditText.setText(value2.toString())
                row2LeftEditText.setText(value3.toString())
                row2RightEditText.setText(value4.toString())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_var07_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val setButton = findViewById<Button>(R.id.set)
        val randomButton = findViewById<Button>(R.id.random)

        row1LeftEditText = findViewById(R.id.row_1_left_edit_text)
        row1RightEditText = findViewById(R.id.row_1_right_edit_text)
        row2LeftEditText = findViewById(R.id.row_2_left_edit_text)
        row2RightEditText = findViewById(R.id.row_2_right_edit_text)

        savedInstanceState?.let {
            lastResult = it.getDouble(KEY_LAST_RESULT, 0.0)
            lastOperation = it.getString(KEY_LAST_OPERATION, "")
            Log.d("PracticalTest01Var07", "Restored state: $lastOperation = $lastResult")
        }

        randomButton.setOnClickListener {
            if (row1LeftEditText.text.toString().isEmpty()) {
                row1LeftEditText.setText(String.format("%.2f", Random.nextFloat() * 10))
            }
            if (row1RightEditText.text.toString().isEmpty()) {
                row1RightEditText.setText(String.format("%.2f", Random.nextFloat() * 10))
            }
            if (row2LeftEditText.text.toString().isEmpty()) {
                row2LeftEditText.setText(String.format("%.2f", Random.nextFloat() * 10))
            }
            if (row2RightEditText.text.toString().isEmpty()) {
                row2RightEditText.setText(String.format("%.2f", Random.nextFloat() * 10))
            }
        }

        setButton.setOnClickListener {
            val intent = Intent(this, PracticalTest01Var07SecondaryActivity::class.java)
            intent.putExtra("row1Left", row1LeftEditText.text.toString())
            intent.putExtra("row1Right", row1RightEditText.text.toString())
            intent.putExtra("row2Left", row2LeftEditText.text.toString())
            intent.putExtra("row2Right", row2RightEditText.text.toString())
            secondaryActivityLauncher.launch(intent)
        }

        val filter = IntentFilter(PracticalTest01Var07Service.ACTION_UPDATE_VALUES)
        ContextCompat.registerReceiver(
            this,
            broadcastReceiver,
            filter,
            ContextCompat.RECEIVER_NOT_EXPORTED
        )

        val serviceIntent = Intent(this, PracticalTest01Var07Service::class.java)
        startService(serviceIntent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putDouble(KEY_LAST_RESULT, lastResult)
        outState.putString(KEY_LAST_OPERATION, lastOperation)
        Log.d("PracticalTest01Var07", "Saved state: $lastOperation = $lastResult")
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)
        val serviceIntent = Intent(this, PracticalTest01Var07Service::class.java)
        stopService(serviceIntent)
    }
}