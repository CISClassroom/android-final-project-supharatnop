package th.ac.kku.cis.mobileapp.myficprojectapplication

class AddModelNameFic {
    companion object Factory {
        fun create(): AddModelNameFic = AddModelNameFic()
    }

    var AddNameFic: String? = null

}

class AddFicModel { companion object Factory {
    fun create(): AddFicModel = AddFicModel()
}

    var TopicName : String? = null

    var EpName : String? = null
    var Story : String? = null
    var Id : String? = null

}