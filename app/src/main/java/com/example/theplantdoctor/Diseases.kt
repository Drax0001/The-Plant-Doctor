package com.example.theplantdoctor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Diseases : AppCompatActivity() {
    private var searchList = mutableListOf<String>()
    private var titleList = mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var imagesList = mutableListOf<Int>()
    private var treatmentList = mutableListOf<String>()

    val descMutableList = mutableListOf<String>(
        "Powdery mildew leaves a telltale white dusty coating on leaves, stems and flowers. Caused by a fungus, it affects a number of plants, including lilacs, apples, grapes, cucumbers, peas, phlox, daisies and roses.",
        "Downy mildew is caused by fungus-like organisms and affects many ornamentals and edibles, such as impatiens, pansies, columbine, grapevines, lettuce and cole crops such as broccoli and cauliflower. Often occurring during wet weather, downy mildew causes the upper portion of leaves to discolor, while the bottoms develop white or gray mold.",
        "Black spot is a fungal disease commonly found on roses, but also on other flowers and fruits. While it doesn’t kill plants outright, it weakens them and makes them susceptible to other problems. In cool, moist weather, small black spots appear on foliage, which starts to turn yellow and eventually drops off.",
        "There are a number of mosaic viruses, but gardeners are most likely to encounter two: tomato mosaic virus and tobacco mosaic virus. The former infects tomatoes, peppers, potatoes, apples, pears and cherries; the latter infects tomatoes, peppers, cucumbers, lettuce, beets, petunias and, of course, tobacco.",
        "Damping-off disease, caused by several soil-borne fungi, is most problematic in wet, humid conditions. It infects seedlings and causes them to collapse and decay. It’s often found in greenhouses but can occur outdoors as well.",
        "Caused by a soil-borne fungus, fusarium wilt affects ornamental and edible plants, including dianthus, beans, tomatoes, peas and asparagus. The disease causes wilted leaves and stunted plants, as well as root rot and sometimes blackened stem rot. It’s especially active in hot summer temperatures.",
        "Verticillium wilt is a fungal disease that affects hundreds of species of trees, shrubs, edibles and ornamentals (see this list of susceptible plants). Pathogens, which can live in the soil for years, make their way into the plant through the roots, eventually clogging the vascular system and causing branches to wilt suddenly and foliage to turn yellow and fall off prematurely. It can also lead to stunted growth."
    )

    val treatmentMutableList = mutableListOf<String>(
        "Rake up and destroy infected leaves to reduce the spread of spores. Also, give plants good drainage and ample air circulation. Avoid overhead watering at night; mid-morning is preferred to allow foliage to dry before evening. Commercial fungicides are available for powdery mildew, or you can spray with a solution of one tsp. baking soda and one quart of water as recommended by George “Doc” and Katy Abraham, authors of The Green Thumb Garden Handbook.",
        "Plant resistant cultivars when available. No fungicides are available, but cultural practices can help. Remove and destroy infected foliage, or entire plants if downy mildew is prevalent. Avoid crowding plants or watering them in the evening, and rotate edibles year to year.",
        "The fungus overwinters in diseased canes and leaves, so remove both before winter. Keep foliage clean and dry by mulching beneath plants, positioning roses where the morning sun will quickly evaporate dew, and watering at the roots rather than wetting the foliage. Consider planting varieties of roses that are resistant to black spot. Plants also can be sprayed with a fungicide to prevent black spot.",
        "There are no chemical controls, but resistant varieties exist. The virus can live in dry soil for some time. Remove and destroy infected plants, roots and all, and avoid planting susceptible plants in the same area for two years. Because tobacco is a carrier, smokers should wash hands thoroughly before handling plants.",
        "There is no treatment, but you can prevent it with good cultural practices. Use new pots, cell packs or trays, or those disinfected with a 10 percent bleach solution. Fill with fresh, bagged, soilless potting mix, avoid crowding seedlings and provide adequate ventilation.",
        "There are no chemical controls available to home gardeners, but there are disease-resistant cultivars. If fusarium wilt shows up somewhere in the garden, remove and destroy infected plants and do not plant the same species in that spot for five years.",
        "Fungicides are not effective, but good sanitation practices may help. Remove and destroy infected annuals, perennials and edibles. Prune diseased branches off trees and shrubs. (When you do this, you’ll notice a telltale green streak or stain in the wood.) Sterilizing cutting tools with a 10 percent bleach solution between cuts."
    )

    val titleMutableList = mutableListOf<String>(
        "Powdery Mildew", "Downy Mildew", "Black Spot", "Mosaic Viruses", "Damping-off Disease", "Fusarium Wilt", "Verticillium Wilt"
    )
    val imagesMutableList = mutableListOf<Int>(
        R.drawable.powdery_mildew, R.drawable.downy_mildew, R.drawable.black_spot, R.drawable.tobacco_mosaic_virus, R.drawable.damping_off_disease, R.drawable.fusarium_wilt, R.drawable.verticillium_fungi
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diseases)

        supportActionBar?.hide()

        postToList()

        val diseaseCards = findViewById<RecyclerView>(R.id.rv_disease_cards)

        diseaseCards.layoutManager = LinearLayoutManager(this)
        diseaseCards.adapter = RecyclerAdapter(titleList, descList, imagesList)
    }

    private fun addToList(title: String, description: String, image: Int, treatment: String){
        titleList.add(title)
        descList.add(description)
        imagesList.add(image)
        treatmentList.add(treatment)
    }

    private fun postToList() {
        for (i in 0..6) {
            addToList(titleMutableList[i], descMutableList[i], imagesMutableList[i], treatmentMutableList[i])
        }
    }
}