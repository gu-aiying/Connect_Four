fun names(namesList: List<String>): List<String> {

    val nameCount = mutableListOf<String>()
    val mutableNameList = namesList.toMutableList()
    while (mutableNameList.isNotEmpty()) {
        val name = mutableNameList[0]
        val count = mutableNameList.count { it == name }
        nameCount.add("The name $name is repeated $count times")
        mutableNameList.removeAll { it == name }
    }

    return nameCount
}



