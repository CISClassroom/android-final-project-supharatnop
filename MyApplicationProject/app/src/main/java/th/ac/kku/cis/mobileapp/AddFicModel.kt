package th.ac.kku.cis.mobileapp

class AddFicModel { companion object Factory {
    fun create(): AddFicModel = AddFicModel()
}

    var TopicName : String? = null

    var EpName : String? = null
    var Story : String? = null
    var Id : String? = null

}

class NameficModel (var namefic:String)