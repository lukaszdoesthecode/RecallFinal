package com.example.recall.bnt

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.recall.R
import com.example.recall.main.Snackbar
import org.apache.commons.text.similarity.LevenshteinDistance
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * BNT class is responsible for the Boston Naming Test.
 */
class BNT : AppCompatActivity() {

    private lateinit var rootView: View

    private lateinit var image: ImageView
    private lateinit var patientResponse: EditText
    private lateinit var submit: Button
    private lateinit var clue: Button

    private val drawings = listOf(
        DrawingObject(1, "apple", R.drawable.apple),
        DrawingObject(2, "pencil", R.drawable.pencil),
        DrawingObject(3, "bed", R.drawable.bed),
        DrawingObject(4, "giraffe", R.drawable.giraffe),
        DrawingObject(5, "hammer", R.drawable.hammer),
        DrawingObject(6, "igloo", R.drawable.igloo),
        DrawingObject(7, "kite", R.drawable.kite),
        DrawingObject(8, "lamp", R.drawable.lamp),
        DrawingObject(9, "needle", R.drawable.needle),
        DrawingObject(10, "ostrich", R.drawable.ostrich),
        DrawingObject(11, "pineapple", R.drawable.pineapple),
        DrawingObject(12, "scissors", R.drawable.scissors),
        DrawingObject(13, "television", R.drawable.television),
        DrawingObject(14, "umbrella", R.drawable.umbrella),
        DrawingObject(15, "violin", R.drawable.violin),
        DrawingObject(16, "whale", R.drawable.whale),
        DrawingObject(17, "xylophone", R.drawable.xylophone),
        DrawingObject(18, "yarn", R.drawable.yarn),
        DrawingObject(19, "zipper", R.drawable.zipper),
        DrawingObject(20, "balloon", R.drawable.balloon),
        DrawingObject(21, "candle", R.drawable.candle),
        DrawingObject(22, "dog", R.drawable.dog),
        DrawingObject(23, "elephant", R.drawable.elephant),
        DrawingObject(24, "frog", R.drawable.frog),
        DrawingObject(25, "glasses", R.drawable.frog),
        DrawingObject(26, "hat", R.drawable.hat),
        DrawingObject(27, "ice cream", R.drawable.ice_cream),
        DrawingObject(28, "jacket", R.drawable.jacket),
        DrawingObject(29, "key", R.drawable.key),
        DrawingObject(30, "lion", R.drawable.lion),
        DrawingObject(31, "monkey", R.drawable.monkey),
        DrawingObject(32, "nest", R.drawable.nest),
        DrawingObject(33, "orange", R.drawable.orange),
        DrawingObject(34, "quilt", R.drawable.quilt),
        DrawingObject(35, "rocket", R.drawable.rocket),
        DrawingObject(36, "snake", R.drawable.snake),
        DrawingObject(37, "tiger", R.drawable.tiger),
        DrawingObject(38, "unicorn", R.drawable.unicorn),
        DrawingObject(39, "vase", R.drawable.vase),
        DrawingObject(40, "window", R.drawable.window),
        DrawingObject(41, "yacht", R.drawable.yacht),
        DrawingObject(42, "zebra", R.drawable.zebra),
        DrawingObject(43, "ant", R.drawable.ant),
        DrawingObject(44, "book", R.drawable.book),
        DrawingObject(45, "chair", R.drawable.chair),
        DrawingObject(46, "drum", R.drawable.drum),
        DrawingObject(47, "envelope", R.drawable.envelope),
        DrawingObject(48, "fork", R.drawable.fork),
        DrawingObject(49, "grapes", R.drawable.grapes),
        DrawingObject(50, "house", R.drawable.house),
        DrawingObject(51, "insect", R.drawable.insect),
        DrawingObject(52, "jellyfish", R.drawable.jellyfish),
        DrawingObject(53, "kangaroo", R.drawable.kangaroo),
        DrawingObject(54, "lemon", R.drawable.lemon),
        DrawingObject(55, "mountain", R.drawable.mountain),
        DrawingObject(56, "nut", R.drawable.nut),
        DrawingObject(57, "penguin", R.drawable.penguin),
        DrawingObject(58, "quail", R.drawable.quail),
        DrawingObject(59, "rainbow", R.drawable.rainbow),
        DrawingObject(60, "star", R.drawable.star)
    ).shuffled()
    private val cues = listOf(
        Pair("apple", "fruit"),
        Pair("pencil", "something to write with"),
        Pair("bed", "furniture to sleep on"),
        Pair("giraffe", "tall animal with a long neck"),
        Pair("hammer", "tool used to hit nails"),
        Pair("igloo", "snow house"),
        Pair("kite", "flies in the sky with a string"),
        Pair("lamp", "provides light on a table"),
        Pair("needle", "used for sewing"),
        Pair("ostrich", "large bird that cannot fly"),
        Pair("pineapple", "spiky tropical fruit"),
        Pair("scissors", "tool to cut paper"),
        Pair("television", "screen for watching shows"),
        Pair("umbrella", "used to stay dry in the rain"),
        Pair("violin", "string instrument played with a bow"),
        Pair("whale", "large ocean mammal"),
        Pair("xylophone", "musical instrument with bars"),
        Pair("yarn", "used for knitting"),
        Pair("zipper", "fastener for clothes"),
        Pair("balloon", "inflatable decoration"),
        Pair("candle", "wax stick that burns for light"),
        Pair("dog", "man's best friend"),
        Pair("elephant", "large animal with a trunk"),
        Pair("frog", "amphibian that jumps"),
        Pair("glasses", "worn to improve vision"),
        Pair("hat", "worn on the head"),
        Pair("ice cream", "cold dessert in a cone"),
        Pair("jacket", "outerwear to keep warm"),
        Pair("key", "used to unlock doors"),
        Pair("lion", "big cat with a mane"),
        Pair("monkey", "primate that swings in trees"),
        Pair("nest", "bird's home"),
        Pair("orange", "citrus fruit"),
        Pair("quilt", "bed covering made of patches"),
        Pair("rocket", "launches into space"),
        Pair("snake", "long, slithering reptile"),
        Pair("tiger", "striped big cat"),
        Pair("unicorn", "mythical horse with a horn"),
        Pair("vase", "holds flowers"),
        Pair("window", "opening in a wall for light"),
        Pair("yacht", "luxury boat"),
        Pair("zebra", "striped animal"),
        Pair("ant", "small insect in a colony"),
        Pair("book", "collection of written pages"),
        Pair("chair", "furniture to sit on"),
        Pair("drum", "percussion instrument"),
        Pair("envelope", "used to mail letters"),
        Pair("fork", "utensil to eat with"),
        Pair("grapes", "small round fruit in clusters"),
        Pair("house", "building to live in"),
        Pair("insect", "small six-legged creature"),
        Pair("jellyfish", "sea creature with tentacles"),
        Pair("kangaroo", "marsupial that hops"),
        Pair("lemon", "sour citrus fruit"),
        Pair("mountain", "high, rocky elevation"),
        Pair("nut", "hard-shelled seed"),
        Pair("penguin", "flightless bird in the cold"),
        Pair("quail", "small bird often hunted"),
        Pair("star", "bright object in the night sky")
    )

    private var currentDrawingIndex = 0
    private val responses = mutableListOf<Response>()
    private var startTime: Long = 0
    private var cuesUsed: Int = 0
    private var cueCorrectCount = 0

    /**
     * onCreate function initializes the activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bnt)

        rootView = findViewById(android.R.id.content)

        image = findViewById(R.id.image)
        patientResponse = findViewById(R.id.response)
        submit = findViewById(R.id.submit)
        clue = findViewById(R.id.clue)

        loadNextDrawing()

        submit.setOnClickListener {
            submitResponse()
        }

        clue.setOnClickListener {
            provideClue()
        }
    }

    /**
     * loadNextDrawing function loads the next drawing in the list.
     */
    private fun loadNextDrawing() {
        if (currentDrawingIndex < drawings.size) {
            val currentDrawing = drawings[currentDrawingIndex]
            image.setImageResource(currentDrawing.imageResId)
            patientResponse.text.clear()
            startTime = SystemClock.elapsedRealtime()
        } else {
            calculateScore()
        }
    }

    /**
     * submitResponse function submits the patient's response.
     */
    private fun submitResponse() {
        val responseText = patientResponse.text.toString().trim()
        if (responseText.isNotEmpty()) {
            val responseTime = SystemClock.elapsedRealtime() - startTime
            val response = Response(
                objectId = drawings[currentDrawingIndex].id,
                responseText = responseText,
                responseTime = responseTime,
                cueUsed = cuesUsed
            )
            responses.add(response)

            if (cuesUsed > 0 && responseText.equals(drawings[currentDrawingIndex].name, ignoreCase = true)) {
                cueCorrectCount++
            }
            currentDrawingIndex++
            loadNextDrawing()
        } else {
            Snackbar.showSnackbar(rootView, "Please enter a response")
        }
    }

    /**
     * provideClue function provides a clue for the current drawing.
     */
    private fun provideClue() {
        cuesUsed += 1
        val currentDrawing = drawings[currentDrawingIndex]
        val matchingClue = cues.find { it.first == currentDrawing.name }
        Snackbar.showSnackbar(rootView, matchingClue?.second ?: "No clue available")


    }

    /**
     * calculateScore function calculates the patient's score.
     */
    private fun calculateScore() {
        var totalCorrect = 0
        var phonemicErrors = 0
        var semanticErrors = 0
        var perseverativeErrors = 0
        val previousResponses = mutableSetOf<String>()

        responses.forEach { response ->
            val drawing = drawings.first { it.id == response.objectId }
            if (response.responseText.equals(drawing.name, ignoreCase = true)) {
                totalCorrect++
            } else {
                if (previousResponses.contains(response.responseText)) {
                    perseverativeErrors++
                } else {
                    if (isPhonemicError(response.responseText, drawing.name)) {
                        phonemicErrors++
                    } else if (isSemanticError(response.responseText, drawing.name)) {
                        semanticErrors++
                    }
                }
            }
            previousResponses.add(response.responseText)
        }

        val score = DataBNT(
            noTests = 1,
            totalCorrect = totalCorrect,
            totalErrors = phonemicErrors + semanticErrors + perseverativeErrors,
            phoneticErrors = phonemicErrors,
            semanticErrors = semanticErrors,
            perseverativeErrors = perseverativeErrors,
            cueUtilization = cueCorrectCount,
            date = getCurrentDate()
        )

        val intent = Intent(this, ScoreBNT::class.java).apply {
            putExtra("totalCorrect", totalCorrect)
            putExtra("totalErrors", phonemicErrors + semanticErrors + perseverativeErrors)
            putExtra("phoneticErrors", phonemicErrors)
            putExtra("semanticErrors", semanticErrors)
            putExtra("perseverativeErrors", perseverativeErrors)
            putExtra("cueUtilization", cueCorrectCount)
            putExtra("date", SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(score.date))
        }
        startActivity(intent)
    }

    /**
     * isPhonemicError function checks if the response is a phonemic error.
     */
    private fun isPhonemicError(response: String, correct: String): Boolean {
        val threshold = 2
        val levenshteinDistance = LevenshteinDistance(threshold)
        val distance = levenshteinDistance.apply(response, correct)
        return distance != -1
    }

    /**
     * isSemanticError function checks if the response is a semantic error.
     */
    private fun isSemanticError(response: String, correct: String): Boolean {
        val semanticMap = mapOf(
            "apple" to listOf("fruit", "food"),
            "pencil" to listOf("pen", "writing tool"),
            "bed" to listOf("sleeping furniture", "bunk"),
            "giraffe" to listOf("tall animal", "long-necked animal"),
            "hammer" to listOf("mallet", "tool"),
            "igloo" to listOf("snow house", "ice house"),
            "kite" to listOf("flying toy", "wind toy"),
            "lamp" to listOf("light", "lantern"),
            "needle" to listOf("sewing tool", "pin"),
            "ostrich" to listOf("large bird", "flightless bird"),
            "pineapple" to listOf("tropical fruit", "exotic fruit"),
            "scissors" to listOf("cutting tool", "shears"),
            "television" to listOf("TV", "screen"),
            "umbrella" to listOf("parasol", "rain shield"),
            "violin" to listOf("fiddle", "string instrument"),
            "whale" to listOf("ocean giant", "sea mammal"),
            "xylophone" to listOf("percussion instrument", "musical instrument"),
            "yarn" to listOf("knitting material", "thread"),
            "zipper" to listOf("fastener", "closure"),
            "balloon" to listOf("inflatable", "blimp"),
            "candle" to listOf("wax light", "taper"),
            "dog" to listOf("canine", "puppy"),
            "elephant" to listOf("pachyderm", "large mammal"),
            "frog" to listOf("amphibian", "toad"),
            "glasses" to listOf("spectacles", "eyewear"),
            "hat" to listOf("headwear", "cap"),
            "ice cream" to listOf("frozen dessert", "gelato"),
            "jacket" to listOf("coat", "outerwear"),
            "key" to listOf("unlocker", "passkey"),
            "lion" to listOf("big cat", "wild cat"),
            "monkey" to listOf("primate", "ape"),
            "nest" to listOf("bird home", "roost"),
            "orange" to listOf("citrus", "fruit"),
            "quilt" to listOf("bedcover", "blanket"),
            "rocket" to listOf("missile", "spacecraft"),
            "snake" to listOf("serpent", "reptile"),
            "tiger" to listOf("striped feline", "big cat"),
            "unicorn" to listOf("mythical horse", "fantasy horse"),
            "vase" to listOf("flower holder", "urn"),
            "window" to listOf("pane", "casement"),
            "yacht" to listOf("boat", "vessel"),
            "zebra" to listOf("striped animal", "wild horse"),
            "ant" to listOf("insect", "bug"),
            "book" to listOf("novel", "volume"),
            "chair" to listOf("seat", "stool"),
            "drum" to listOf("percussion", "tambour"),
            "envelope" to listOf("mailing cover", "packet"),
            "fork" to listOf("eating utensil", "prong"),
            "grapes" to listOf("vine fruit", "berry"),
            "house" to listOf("home", "residence"),
            "insect" to listOf("bug", "arthropod"),
            "jellyfish" to listOf("sea creature", "medusa"),
            "kangaroo" to listOf("marsupial", "joey's mother"),
            "lemon" to listOf("citrus fruit", "sour fruit"),
            "mountain" to listOf("peak", "summit"),
            "nut" to listOf("seed", "kernel"),
            "penguin" to listOf("flightless bird", "sea bird"),
            "quail" to listOf("small bird", "game bird"),
            "star" to listOf("celestial body", "heavenly body")
        )
        return semanticMap[correct]?.contains(response) == true
    }

    /**
     * getCurrentDate function gets the current date.
     */
    private fun getCurrentDate(): Date {
        val currentDate = Date()
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(currentDate)
        return dateFormat.parse(formattedDate) ?: currentDate
    }
}
