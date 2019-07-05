package com.kalyan.otpview

import android.content.ClipboardManager
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.View.OnLongClickListener
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

class OtpEntryView(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    private val entryOne: EditText
    private val entryTwo: EditText
    private val entryThree: EditText
    private val entryFour: EditText
    private val entryFive: EditText

    private val otpString = arrayOf<CharSequence>("", "", "", "", "")

    constructor(context: Context) : this(context, null)

    init {
        /*val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.layout_pin_entry, this, true)*/
        View.inflate(context, R.layout.layout_otp_entry, this)

        entryOne = findViewById(R.id.otp_entry_one)
        entryTwo = findViewById(R.id.otp_entry_two)
        entryThree = findViewById(R.id.otp_entry_three)
        entryFour = findViewById(R.id.otp_entry_four)
        entryFive = findViewById(R.id.otp_entry_five)

        /*val observables = listOf(RxTextView.textChanges(entryOne),
                        RxTextView.textChanges(entryTwo),
                        RxTextView.textChanges(entryThree),
                        RxTextView.textChanges(entryFour),
                        RxTextView.textChanges(entryFive))

        Observable.merge(observables)
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableObserver<CharSequence>() {
                                override fun onComplete() {}

                                override fun onNext(charSequence: CharSequence) {
                                        Timber.d("OtpEntryView OnNext Charsequence: $charSequence")

                                        otpString = "${entryOne.text}${entryTwo.text}${entryThree.text}${entryFour.text}${entryFive.text}"

                                        Timber.d("OtpEntryView OnNext otpString: $otpString")

                                        when (otpString.length) {
                                                0 -> entryOne.requestFocus()
                                                1 -> entryTwo.requestFocus()
                                                2 -> entryThree.requestFocus()
                                                3 -> entryFour.requestFocus()
                                                4 -> entryFive.requestFocus()
                                        }
                                }

                                override fun onError(e: Throwable) {
                                        Timber.e(e)
                                }

                        })
                        .addTo(disposeBag)*/

        entryOne.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                otpString[0] = s
                otpString.joinToString("")
                if (s.isNotBlank()) {
                    entryTwo.requestFocus()
                }
            }
        })

        entryTwo.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                otpString[1] = s
                otpString.joinToString("")
                if (s.isNotBlank()) {
                    entryThree.requestFocus()
                }
            }
        })

        entryThree.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                otpString[2] = s
                otpString.joinToString("")
                if (s.isNotBlank()) {
                    entryFour.requestFocus()
                }
            }
        })

        entryFour.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                otpString[3] = s
                otpString.joinToString("")
                if (s.isNotBlank()) {
                    entryFive.requestFocus()
                }
            }
        })

        entryFour.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                otpString[3] = s
                otpString.joinToString("")
                if (s.isNotBlank()) {
                    entryFive.requestFocus()
                }
            }
        })

        entryFive.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                otpString[4] = s
                otpString.joinToString("")
                if (s.isNotBlank()) {
                    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(entryFive.windowToken, 0)
                }
            }
        })


        entryFive.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {
                if (entryFive.text.isBlank()) {
                    if (keyCode == KeyEvent.KEYCODE_DEL) {
                        entryFour.setText("")
                        entryFour.requestFocus()
                        return@setOnKeyListener true
                    }
                }
            }
            false
        }

        entryFour.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {
                if (entryFour.text.isBlank()) {
                    if (keyCode == KeyEvent.KEYCODE_DEL) {
                        entryThree.setText("")
                        entryThree.requestFocus()
                        return@setOnKeyListener true
                    }
                }
            }
            false
        }

        entryThree.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {
                if (entryThree.text.isBlank()) {
                    if (keyCode == KeyEvent.KEYCODE_DEL) {
                        entryTwo.setText("")
                        entryTwo.requestFocus()
                        return@setOnKeyListener true
                    }
                }
            }
            false
        }

        entryTwo.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {
                if (entryTwo.text.isBlank()) {
                    if (keyCode == KeyEvent.KEYCODE_DEL) {
                        entryOne.setText("")
                        entryOne.requestFocus()
                        return@setOnKeyListener true
                    }
                }
            }
            false
        }

        /*RxView.keys(entryTwo)
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                                if (entryTwo.text.isBlank()) {
                                        if (it.keyCode == KeyEvent.KEYCODE_DEL) {
                                                entryOne.text = null
                                                entryOne.requestFocus()
                                        }
                                }
                        }.addTo(disposeBag)*/


        findViewById<ConstraintLayout>(R.id.otp_entry_root_container).setOnLongClickListener {
            copyPasteOTP()
        }

        val longClickListener = OnLongClickListener {
            copyPasteOTP()
        }
        entryOne.setOnLongClickListener(longClickListener)
        entryTwo.setOnLongClickListener(longClickListener)
        entryThree.setOnLongClickListener(longClickListener)
        entryFour.setOnLongClickListener(longClickListener)
        entryFive.setOnLongClickListener(longClickListener)
    }

    private fun copyPasteOTP(): Boolean {
        return try {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val copiedOtpText = clipboard.primaryClip?.getItemAt(0)?.text
            copiedOtpText?.let {
                try {
                    it.toString().toInt()
                    if (it.length >= 5) {
                        entryOne.setText("${it[0]}")
                        entryTwo.setText("${it[1]}")
                        entryThree.setText("${it[2]}")
                        entryFour.setText("${it[3]}")
                        entryFive.setText("${it[4]}")
                    } else {
                        Toast.makeText(context, "OTP not copied", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: NumberFormatException) {
                    Toast.makeText(context, "OTP not copied", Toast.LENGTH_SHORT).show()
                }
            }
            true
        } catch (e: Exception) {
            Log.e("OtpEntryView", e.stackTrace.toString())
            false
        }
    }


    fun fetchOtpString() = otpString.joinToString("")

    fun focusWithKeyboard() {
        entryOne.requestFocus()
        toggleKeyboard(hide = false)
    }

    private fun toggleKeyboard(hide: Boolean) {
        try {
            val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            val view = entryOne
            if (hide) {
                inputManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            } else {
                inputManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
            }
        } catch (e: Exception) {
            Log.e("OtpEntryView", e.stackTrace.toString())
        }
    }

    //fun otpStringObservable(): Observable<String> = Observable.fromIterable(otpString.iterator())

}