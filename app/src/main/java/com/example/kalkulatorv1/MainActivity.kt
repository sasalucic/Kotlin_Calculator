package com.example.kalkulatorv1
import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.log10
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan


class MainActivity : AppCompatActivity() {

    private lateinit var inputBox: TextView
    private lateinit var outputBox: TextView
    private lateinit var backspace: Button
    private lateinit var delete: Button
    private lateinit var one: Button
    private lateinit var two: Button
    private lateinit var three: Button
    private lateinit var four: Button
    private lateinit var five: Button
    private lateinit var six: Button
    private lateinit var seven: Button
    private lateinit var eight: Button
    private lateinit var nine: Button
    private lateinit var zero: Button
    private lateinit var equals: Button
    private lateinit var plus: Button
    private lateinit var minus: Button
    private lateinit var divide: Button
    private lateinit var multiply: Button
    private lateinit var dot: Button
    private lateinit var squareRoot: Button
    private lateinit var percentage : Button
    private lateinit var power : Button
    private lateinit var powerOfTwo: Button
    private lateinit var oneDivided: Button
    private lateinit var piButton: Button
    private lateinit var tenPowerX: Button
    private lateinit var factorial: Button
    private lateinit var mod: Button
    private lateinit var modeChange: Button
    private lateinit var plusMinus: Button
    private lateinit var sin: Button
    private lateinit var cos: Button
    private lateinit var tan: Button
    private lateinit var ln: Button
    private lateinit var log: Button
    private lateinit var powerOfThree: Button

    private lateinit var leftBracket: Button
    private lateinit var rightBracket: Button
    private var openBracketCount = 0
    private var closedBracketCount = 0
    private var firstNumber: Double? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        // Hide the status bar
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        // Hide the action bar
        supportActionBar?.hide()
        // Hide the navigation bar
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

        // Set an OnSystemUiVisibilityChangeListener to detect when the system UI reappears
        window.decorView.setOnSystemUiVisibilityChangeListener { visibility ->
            if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                // The system UI reappeared, show the navigation bar with a slide
                window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            }
        }
        super.onCreate(savedInstanceState)

        // Determine the current device orientation
        val orientation = resources.configuration.orientation


        // Set the layout file based on the device orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main_land)
            squareRoot = findViewById(R.id.btnSqrt)
            squareRoot.setOnClickListener { squareRootCalculate() }

            percentage = findViewById(R.id.btnPercent)
            percentage.setOnClickListener { percentageCalculate() }

            power = findViewById(R.id.btnPower)
            power.setOnClickListener { powerOf() }

            powerOfTwo = findViewById(R.id.btnPowerOfTwo)
            powerOfTwo.setOnClickListener { powerOfTwo() }

            oneDivided = findViewById(R.id.btnOneDivided)
            oneDivided.setOnClickListener { oneDividedBy() }

            piButton = findViewById(R.id.btnPi)
            piButton.setOnClickListener {piNumber()}

            tenPowerX = findViewById(R.id.btnTenOnX)
            tenPowerX.setOnClickListener { tenOnX() }

            factorial = findViewById(R.id.btnFactorial)
            factorial.setOnClickListener {factorialCalculate()}

            mod = findViewById(R.id.btnMod)
            mod.setOnClickListener { appendOnClick(false,"%") }

            plusMinus = findViewById(R.id.btnPlusMinus)
            plusMinus.setOnClickListener { plusMinus()}

            sin = findViewById(R.id.btnSin)
            sin.setOnClickListener {calculateSin()}

            cos = findViewById(R.id.btnCos)
            cos.setOnClickListener {calculateCos()}

            tan = findViewById(R.id.btnTan)
            tan.setOnClickListener { calculateTan() }

            ln = findViewById(R.id.btnLn)
            ln.setOnClickListener { calculateLn() }

            log = findViewById(R.id.btnLog)
            log.setOnClickListener { calculateLog() }

            powerOfThree = findViewById(R.id.btnPowerOfThree)
            powerOfThree.setOnClickListener { powerOfThree() }
        } else {
            setContentView(R.layout.activity_main)
        }

        modeChange = findViewById(R.id.btnModeChange)
        modeChange.setOnClickListener { modeChangeOrientation() }

        inputBox = findViewById(R.id.tvInput)
        outputBox = findViewById(R.id.tvOutput)
        backspace = findViewById(R.id.btnBackspace)
        delete = findViewById(R.id.btnClear)
        one = findViewById(R.id.btnOne)
        two = findViewById(R.id.btnTwo)
        three = findViewById(R.id.btnThree)
        four = findViewById(R.id.btnFour)
        five = findViewById(R.id.btnFive)
        six = findViewById(R.id.btnSix)
        seven = findViewById(R.id.btnSeven)
        eight = findViewById(R.id.btnEight)
        nine = findViewById(R.id.btnNine)
        zero = findViewById(R.id.btnZero)
        equals = findViewById(R.id.btnEquals)
        plus = findViewById(R.id.btnPlus)
        minus = findViewById(R.id.btnMinus)
        divide = findViewById(R.id.btnDivide)
        multiply = findViewById(R.id.btnMultiply)
        dot = findViewById(R.id.btnDot)

        leftBracket = findViewById(R.id.btnLeftB)
        rightBracket = findViewById(R.id.btnRightB)

        zero.setOnClickListener { appendOnClick(true, "0") }
        one.setOnClickListener { appendOnClick(true, "1") }
        two.setOnClickListener { appendOnClick(true, "2") }
        three.setOnClickListener { appendOnClick(true, "3") }
        four.setOnClickListener { appendOnClick(true, "4") }
        five.setOnClickListener { appendOnClick(true, "5") }
        six.setOnClickListener { appendOnClick(true, "6") }
        seven.setOnClickListener { appendOnClick(true, "7") }
        eight.setOnClickListener { appendOnClick(true, "8") }
        nine.setOnClickListener { appendOnClick(true, "9") }
        dot.setOnClickListener { appendOnClick(true, ".") }
        leftBracket.setOnClickListener { appendOnClick(false, "(") }
        rightBracket.setOnClickListener { appendOnClick(false, ")") }
        divide.setOnClickListener { appendOnClick(false, "/") }
        plus.setOnClickListener { appendOnClick(false, "+") }
        minus.setOnClickListener { appendOnClick(false, "-") }
        multiply.setOnClickListener { appendOnClick(false, "*") }



        backspace.setOnClickListener {
            deleteCharacter()
        }


        delete.setOnClickListener {
            clear()
        }

        equals.setOnClickListener {
            val input = inputBox.text.toString()
            if (input.isNotEmpty()) {
                calculate()
                inputBox.text = outputBox.text
                outputBox.text = ""
            }
            openBracketCount = 0
            closedBracketCount = 0
            firstNumber = null
        }

        // Add TextWatcher to inputBox
        // Automatically update the outputBox when the activity starts
        updateOutputBox()

        // Add TextWatcher to inputBox
        inputBox.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                adjustInputFontSize()
                updateOutputBox()
            }
        })

        // Automatically update the outputBox when the activity starts
        updateOutputBox()
    }




    private fun modeChangeOrientation() {
        val orientation = resources.configuration.orientation
        val newOrientation = if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        } else {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        requestedOrientation = newOrientation
    }


    private fun plusMinus() {
        val currentInput = inputBox.text.toString()

        if (currentInput.isNotEmpty()) {
            val firstChar = currentInput[0]
            val newInput = if (firstChar == '-') {
                // Remove the negative sign if it already exists
                currentInput.substring(1)
            } else {
                // Add a negative sign at the beginning
                "-$currentInput"
            }

            inputBox.text = newInput
        }
    }


    private fun tenOnX(){
        val input = inputBox.text.toString()
        val lastChar = if (input.isNotEmpty()) input.last().toString() else ""
        if (lastChar.matches(Regex("[+\\-*/^%]")) || input.isEmpty()){
            appendOnClick(false,"10^")
        } else {
            appendOnClick(false,"*10^")
        }
    }
    private fun piNumber(){
        val pi = Math.PI

        val input = inputBox.text.toString()
        val lastChar = if (input.isNotEmpty()) input.last().toString() else ""
        if (lastChar.matches(Regex("[+\\-*/^%]")) || input.isEmpty()) {
            inputBox.append(pi.toString())
        } else {
            appendOnClick(false, "*$pi")
        }
    }
    private fun oneDividedBy(){
        val input = inputBox.text.toString()
        val lastChar = if (input.isNotEmpty()) input.last().toString() else ""
        if (lastChar.matches(Regex("[+\\-*/^%]")) || input.isEmpty()) {
                appendOnClick(false,"1/")
        } else {
            appendOnClick(false,"*1/")
        }
    }
    private fun powerOfThree(){
        val input = inputBox.text.toString()
        if (input.isNotEmpty()){
            appendOnClick(false, "^(3)")
        } else{
            Toast.makeText(this, "Add operator after number", Toast.LENGTH_SHORT).show()
        }
    }
    private fun powerOfTwo(){
        val input = inputBox.text.toString()
        if (input.isNotEmpty()){
            appendOnClick(false, "^(2)")
        } else{
            Toast.makeText(this, "Add operator after number", Toast.LENGTH_SHORT).show()
        }
    }
    private fun powerOf(){
        val input = inputBox.text.toString()
        if (input.isNotEmpty()) {
            appendOnClick(false, "^")
        }else {
            Toast.makeText(this, "Add power operator after number", Toast.LENGTH_SHORT).show()
        }
    }


    private fun factorialCalculate(){
        val input = inputBox.text.toString()
        val output = outputBox.text.toString()
        if(input.isNotEmpty() && output.isEmpty()){
            try {
                val number = input.toDouble()
                val factorial = factorial(number)
                outputBox.text = formatNumber(factorial)
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Error occurred", Toast.LENGTH_SHORT).show()
            }
        } else if (input.isEmpty()) {
            Toast.makeText(this, "Enter a number before calculating factorial", Toast.LENGTH_SHORT).show()
        }
        if (input.isNotEmpty() && output.isNotEmpty()){
            try {
                inputBox.text = outputBox.text
                val number = output.toDouble()
                val factorial = factorial(number)
                outputBox.text = formatNumber(factorial)
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Error occurred", Toast.LENGTH_SHORT).show()
            }
        } else if (input.isEmpty()) {
            Toast.makeText(this, "Enter a number before calculating factorial", Toast.LENGTH_SHORT).show()
        }
    }

    private fun factorial(n: Double): Double{
        return if (n == 0.0 || n == 1.0){
            1.0
        }else{
            n * factorial(n-1.0)
        }
    }
    private fun percentageCalculate(){
        val input = inputBox.text.toString()
        val result = outputBox.text.toString()

        if (input.isNotEmpty() && result.isEmpty()){
            try {
                val number = input.toDouble()
                val percent = number / 100
                outputBox.text = formatNumber(percent)
            } catch (e: NumberFormatException){
                Toast.makeText(this,"Invalid input", Toast.LENGTH_SHORT).show()
            }catch (e: Exception){
                Toast.makeText(this, "Error occured", Toast.LENGTH_SHORT).show()
            }
        }else if (input.isEmpty()){
            Toast.makeText(this, "Enter a number before percentage operator", Toast.LENGTH_SHORT).show()
        }
        if (input.isNotEmpty() && result.isNotEmpty()){
            try {
                inputBox.text = outputBox.text
                val number = result.toDouble()
                val percent = number / 100
                outputBox.text = formatNumber(percent)
            } catch (e: NumberFormatException){
                Toast.makeText(this,"Invalid input", Toast.LENGTH_SHORT).show()
            }catch (e: Exception){
                Toast.makeText(this, "Error occured", Toast.LENGTH_SHORT).show()
            }
        }else if (input.isEmpty()){
            Toast.makeText(this, "Enter a number before percentage operator", Toast.LENGTH_SHORT).show()
        }
    }

    private fun calculateSin() {
        val input = inputBox.text.toString()
        val output = outputBox.text.toString()
        if (input.isNotEmpty() && output.isEmpty()) {
            try {
                val number = input.toDouble()
                val sin = sin(Math.toRadians(number))
                outputBox.text = formatNumber(sin)
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Error occurred", Toast.LENGTH_SHORT).show()
            }
        } else if (input.isEmpty()) {
            Toast.makeText(this, "Enter a number first", Toast.LENGTH_SHORT).show()
        }
        if (input.isNotEmpty() && output.isNotEmpty()){
            try {
                inputBox.text = outputBox.text
                val number = output.toDouble()
                val sin = sin(Math.toRadians(number))
                outputBox.text = formatNumber(sin)
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Error occurred", Toast.LENGTH_SHORT).show()
            }
        }else if (input.isEmpty()) {
            Toast.makeText(this, "Enter a number first", Toast.LENGTH_SHORT).show()
        }
    }
    private fun calculateCos() {
        val input = inputBox.text.toString()
        val output = outputBox.text.toString()
        if (input.isNotEmpty() && output.isEmpty()) {
            try {
                val number = input.toDouble()
                val sin = cos(Math.toRadians(number))
                outputBox.text = formatNumber(sin)
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Error occurred", Toast.LENGTH_SHORT).show()
            }
        } else if (input.isEmpty()) {
            Toast.makeText(this, "Enter a number first", Toast.LENGTH_SHORT).show()
        }
        if (input.isNotEmpty() && output.isNotEmpty()){
            try {
                inputBox.text = outputBox.text
                val number = output.toDouble()
                val sin = cos(Math.toRadians(number))
                outputBox.text = formatNumber(sin)
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Error occurred", Toast.LENGTH_SHORT).show()
            }
        }else if (input.isEmpty()) {
            Toast.makeText(this, "Enter a number first", Toast.LENGTH_SHORT).show()
        }
    }

    private fun calculateTan() {
        val input = inputBox.text.toString()
        val output = outputBox.text.toString()
        if (input.isNotEmpty() && output.isEmpty()) {
            try {
                val number = input.toDouble()
                val sin = tan(Math.toRadians(number))
                outputBox.text = formatNumber(sin)
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Error occurred", Toast.LENGTH_SHORT).show()
            }
        } else if (input.isEmpty()) {
            Toast.makeText(this, "Enter a number first", Toast.LENGTH_SHORT).show()
        }
        if (input.isNotEmpty() && output.isNotEmpty()){
            try {
                inputBox.text = outputBox.text
                val number = output.toDouble()
                val sin = tan(Math.toRadians(number))
                outputBox.text = formatNumber(sin)
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Error occurred", Toast.LENGTH_SHORT).show()
            }
        }else if (input.isEmpty()) {
            Toast.makeText(this, "Enter a number first", Toast.LENGTH_SHORT).show()
        }
    }
    private fun calculateLn() {
        val input = inputBox.text.toString()
        val output = outputBox.text.toString()
        if (input.isNotEmpty() && output.isEmpty()) {
            try {
                val number = input.toDouble()
                val ln = ln(number)
                outputBox.text = formatNumber(ln)
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Error occurred", Toast.LENGTH_SHORT).show()
            }
        } else if (input.isEmpty()) {
            Toast.makeText(this, "Enter a number first", Toast.LENGTH_SHORT).show()
        }
        if (input.isNotEmpty() && output.isNotEmpty()){
            try {
                inputBox.text = outputBox.text
                val number = output.toDouble()
                val ln = ln(number)
                outputBox.text = formatNumber(ln)
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Error occurred", Toast.LENGTH_SHORT).show()
            }
        } else if (input.isEmpty()) {
            Toast.makeText(this, "Enter a number first", Toast.LENGTH_SHORT).show()
        }
    }
    private fun calculateLog() {
        val input = inputBox.text.toString()
        val output = outputBox.text.toString()
        if (input.isNotEmpty() && output.isEmpty()) {
            try {
                val number = input.toDouble()
                val log = log10(number)
                outputBox.text = formatNumber(log)
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Error occurred", Toast.LENGTH_SHORT).show()
            }
        } else if (input.isEmpty()) {
            Toast.makeText(this, "Enter a number first", Toast.LENGTH_SHORT).show()
        }
        if (input.isNotEmpty() && output.isNotEmpty()){
            try {
                inputBox.text = outputBox.text
                val number = output.toDouble()
                val log = log10(number)
                outputBox.text = formatNumber(log)
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Error occurred", Toast.LENGTH_SHORT).show()
            }
        } else if (input.isEmpty()) {
            Toast.makeText(this, "Enter a number first", Toast.LENGTH_SHORT).show()
        }
    }

    private fun squareRootCalculate(){
        val input = inputBox.text.toString()
        val result = outputBox.text.toString()
        if (input.isNotEmpty() && result.isEmpty()) {
            try {
                val number = input.toDouble()
                val squareRoot = sqrt(number)
                outputBox.text = formatNumber(squareRoot)
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Error occurred", Toast.LENGTH_SHORT).show()
            }
        } else if( input.isEmpty()){
            Toast.makeText(this, "Enter a number first", Toast.LENGTH_SHORT).show()
        }
        if (input.isNotEmpty() && result.isNotEmpty()){
            try {
                inputBox.text = outputBox.text
                val number = result.toDouble()
                val squareRoot = sqrt(number)
                outputBox.text = formatNumber(squareRoot)
            } catch (e: NumberFormatException){
                Toast.makeText(this,"Invalid input", Toast.LENGTH_SHORT).show()
            }catch (e: Exception){
                Toast.makeText(this, "Error occured", Toast.LENGTH_SHORT).show()
            }
        }else if (input.isEmpty()){
            Toast.makeText(this, "Enter a number before percentage operator", Toast.LENGTH_SHORT).show()
        }
    }
    private fun updateOutputBox() {
        val input = inputBox.text.toString()
        try {
            val expression = ExpressionBuilder(input).build()
            val result = expression.evaluate()
            outputBox.text = formatNumber(result)

            outputBox.post {
                val maxWidth = outputBox.width
                val maxFontSize = 25f
                val currentFontSize = outputBox.textSize

                while (outputBox.paint.measureText(outputBox.text.toString()) > maxWidth && currentFontSize > maxFontSize) {
                    outputBox.setTextSize(TypedValue.COMPLEX_UNIT_PX, outputBox.textSize - 1f)
                }
            }
        } catch (e: Exception) {
            outputBox.text = ""
        }
    }


    private fun deleteCharacter() {
        val currentInput = inputBox.text.toString()
        if (currentInput.isNotEmpty()) {
            val lastChar = currentInput.last().toString()
            inputBox.text = currentInput.substring(0, currentInput.length - 1)

            if (lastChar == "(") {
                openBracketCount--
            } else if (lastChar == ")") {
                closedBracketCount--
            }
        }
    }

    private fun appendOnClick(clear: Boolean, string: String) {
        val currentInput = inputBox.text.toString()

        if (clear) {
            outputBox.text = ""
            inputBox.append(string)
        } else {
            val lastChar = if (currentInput.isNotEmpty()) currentInput.last().toString() else ""

            if (lastChar.matches(Regex("[+\\-*/^%]")) && string != "(" && string != ")") {
                // If the last character is an operator and the clicked string is not a bracket, show a toast message
                Toast.makeText(this, "Only one operator is allowed at a time.", Toast.LENGTH_SHORT).show()
            } else {
                if (string == ")" && openBracketCount <= closedBracketCount) {
                    // If the number of closed brackets is already equal to or greater than the number of open brackets,
                    // don't allow adding another closing bracket
                    Toast.makeText(this, "Cannot add more closing brackets.", Toast.LENGTH_SHORT).show()
                } else if (string == ")" && lastChar == "(") {
                    // If the last character is an opened bracket and the clicked string is a closed bracket,
                    // show a toast message
                    Toast.makeText(this, "Cannot add a closing bracket right after an opening bracket.", Toast.LENGTH_SHORT).show()
                } else if (lastChar == "(" && (string == "*" || string == "/" || string == "^")) {
                    // If the last character is an opening bracket and the clicked string is * or /,
                    // show a toast message
                    Toast.makeText(this, "Cannot add * or / right after an opening bracket.", Toast.LENGTH_SHORT).show()
                } else {
                    if (string == "(") {
                        openBracketCount++
                    } else if (string == ")") {
                        closedBracketCount++
                    }

                    if (outputBox.text.isNotEmpty()) {
                        val formattedOutput = outputBox.text.toString().replace(" ", "")
                        inputBox.text = formattedOutput
                        updateOutputBox() // Add this line to update the outputBox with the new input
                    }
                    inputBox.append(string)
                    outputBox.text = ""
                    updateOutputBox() // Add this line to update the outputBox after appending the new string
                }
            }
        }
    }



    private fun clear() {
        inputBox.text = ""
        outputBox.text = ""
        openBracketCount = 0
        closedBracketCount = 0
        firstNumber = null
    }



     private fun calculate() {
        try {
            val input = ExpressionBuilder(inputBox.text.toString()).build()
            val result = input.evaluate()

            val formattedResult = if (result % 1 == 0.0) {
                result.toLong().toString()
            } else {
                formatNumber(result)
            }

            outputBox.text = formattedResult
            updateOutputBox()

            // Reset the text color of the input box
            inputBox.setTextColor(Color.WHITE)
        } catch (e: ArithmeticException) {
            inputBox.setTextColor(Color.WHITE)
        } catch (e: IllegalArgumentException) {
            inputBox.setTextColor(Color.WHITE)
        } catch (e: Exception) {
            inputBox.setTextColor(Color.WHITE)
        }
    }

    private fun formatNumber(number: Double): String {
        val numberString = number.toString()
        val split = numberString.split(".")
        val integerPart = split[0]
        val decimalPart = if (split.size > 1) split[1] else ""
        val formattedIntegerPart = integerPart.reversed().chunked(3).joinToString(" ").reversed()

        return if (decimalPart.isNotEmpty()) {
            "$formattedIntegerPart.$decimalPart"
        } else {
            formattedIntegerPart
        }
    }


    private fun adjustInputFontSize() {
        // Determine the current device orientation
        val orientation = resources.configuration.orientation


        // Set the layout file based on the device orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
        val maxLength = 14 // Maximum length of the input string before font size adjustment
        val defaultFontSize = 40f // Default font size for the input text

        val inputLength = inputBox.text.length
        val fontSize = if (inputLength > maxLength) {
            val scaleFactor = maxLength.toFloat() / inputLength.toFloat()
            defaultFontSize * scaleFactor
        } else {
            defaultFontSize
        }
            inputBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize)
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE){
                val maxLength = 14 // Maximum length of the input string before font size adjustment
                val defaultFontSize = 40f // Default font size for the input text

                val inputLength = inputBox.text.length
                val scaleFactor = maxLength.toFloat() / inputLength.toFloat()

                val fontSize = if (inputLength > maxLength) {
                    defaultFontSize * scaleFactor.coerceAtLeast(1f)
                } else {
                    defaultFontSize
                }

                inputBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize)
            }

        }
    }
