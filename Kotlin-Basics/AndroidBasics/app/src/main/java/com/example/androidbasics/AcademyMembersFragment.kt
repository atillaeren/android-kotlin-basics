package com.example.androidbasics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidbasics.databinding.FragmentAcademyMembersBinding


class AcademyMembersFragment : Fragment() {
    private lateinit var binding: FragmentAcademyMembersBinding
    private val list = List.lists


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentAcademyMembersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //----------------------------  Array  -------------------------------

        //deleteMember()

        //rankByAge()

        //sortByName()

        //olderMemberArray()

        //numberOfAndroidDevs()

        //myIndex()

        //removeByMemberLevel()

        //addMember()

        //highestAge()

        //longestName()

        //sameHoroscope()

        //commonHometown()

        //averageAge()

        //membersContactInfo()

        //sortByMemberLevel()

        //sameTitle("ios dev")


        //-----------------------------  Enum & When  ------------------------------

        //enumAndroidTeam()

        //enumDictionary()

        //teamNames(EnumType.Ios_Development_Team)

        //printInWhen()

        //whenAge(26)

        //promoteMember()

        //averageTeamAge(EnumType.Ios_Development_Team)

        //printMessage()

        //contactInformationTeam(EnumType.Ios_Development_Team)

        //titleAndAgeMessage()


        //-----------------------------  Null Safety  -------------------------------

        //increaseMotivation(3) //---------null safety main function

        //motivateMembers()

        //println(motivationLevelCheck(6))

        //returnMotivationLevel()

        //println(memberAndMotivation("dfdsf", 6))


    }

    //----------------------------  Array  -------------------------------

    private fun deleteMember() {


        //delete the 3rd member
        list.forEach { list ->
            println(list.fullName)
        }

        list.removeAt(2)

        println("--------------after----------------")

        list.forEach { list ->
            println(list.fullName)
        }

    }

    private fun rankByAge() {

        //rank members according to age largest to smallest
        list.forEach { list ->
            println(list.fullName)
        }

        list.sortByDescending { it.age }

        println("--------------after----------------")

        list.forEach { list ->
            println(list)
        }
    }

    private fun sortByName() {

        list.forEach { list ->
            println(list.fullName)
        }

        //sort members according to name
        list.sortByDescending { it.fullName }

        println("--------------after----------------")

        list.forEach { list ->
            println(list)
        }


    }

    private fun olderMemberArray() {

        //Filter the members of the Academy who are older than 24
        list.forEach { list ->
            println(list.fullName)
        }

        val newList = arrayListOf<AndroidBasicsMembers>()

        list.forEach { list ->
            if (list.age > 24) {
                newList.add(list)
            }
        }

        println("--------------after----------------")

        newList.forEach { newList ->
            println("${newList.fullName} = ${newList.age}")
        }

    }

    private fun numberOfAndroidDevs() {

        //Print the total number of Android Developers.
        var count = 0
        list.forEach { list ->
            if (list.title == "android dev") count++
        }
        println(count)

    }

    private fun myIndex() {
        //Find which index you come across

        val index = list.indexOfFirst {
            it.fullName == "atilla"
        }
        println(index)
    }

    private fun addMember() {
        //Add a new member to the array, who is a mentor of the academy
        list.forEach { list ->
            println(list)
        }

        println("--------------after----------------")

        list.add(
            AndroidBasicsMembers(
                "tuna",
                "founder",
                "aries",
                "mentorLevel",
                "izmir",
                30,
                ContactInformation(5554443322, "tuna@gmail.com"),
                EnumType.Ios_Development_Team
            )
        )
        list.forEach { list ->
            println(list.fullName)
        }


    }

    private fun removeByMemberLevel() {

        //Remove all members who have a specific memberLevel, for example, "A1"
        list.forEach { list ->
            println(list)
        }

        println("--------------after----------------")

        list.removeIf { it.memberLevel == "b1" }
        list.forEach { list ->
            println(list)
        }


    }

    private fun highestAge() {

        //find the highest age member
        val oldestMember = list.maxBy { it.age }
        println("Name: ${oldestMember.fullName}, Age: ${oldestMember.age}")


    }

    private fun longestName() {
        //Find the member with the longest name

        var longName = "a"
        list.forEach { list ->
            if (list.fullName.length > longName.length) {
                longName = list.fullName
            }
        }
        println("longest name: $longName and it's length ${longName.length}")
    }

    private fun sameHoroscope() {

        //groupby check----- holds -> map<k,list>
        //keys are horoscope signs, values are members all info
        val horoscopeList = list.groupBy { it.horoscope }

        horoscopeList.forEach { (horoscopeSign, membersSameSign) ->
            println("Members with horoscope sign $horoscopeSign: ")
            membersSameSign.forEach { println(it.fullName) }
        }

    }

    private fun commonHometown() {
        /*
        val newList = arrayListOf<String>()
        list.forEach {
            newList.add(it.homeTown)
        }

        val homeTownCount = newList.groupingBy { it }.eachCount()
        val maxCount = homeTownCount.values.max()
        val mostCommonHomeTown = homeTownCount.filter { it.value == maxCount }.keys.first()
        println(mostCommonHomeTown)
         */
        //println("The most common hometown is ${mostCommonHometown.key}")

        //----------------------------------------------------
        val groupedMembers = list.groupBy { it.homeTown }
        groupedMembers.forEach { (horoscopeSign, membersSameSign) ->
            println("Members with horoscope sign $horoscopeSign: ")
            membersSameSign.forEach { println(it.fullName) }

        }
    }

    private fun averageAge() {

        //find average age
        val totalAge = list.sumOf { it.age }
        println("Average age of members is :'${totalAge / list.size}'")
    }

    private fun membersContactInfo() {
        val newList = arrayListOf<ContactInformation>()

        list.forEach { list ->
            newList.add(list.contact)
        }

        newList.forEach { newList ->
            println(newList.email)
        }
    }

    private fun sortByMemberLevel() {
        //Sort the members according to their memberLevel
        list.sortByDescending { it.memberLevel }
        list.forEach { list ->
            println(list.fullName + " : " + list.memberLevel)
        }
    }

    private fun sameTitle(memberTitle: String) {

        //if input string equal to members title put in a list
        val filteredMembers = list.filter { it.title == memberTitle }
        //put that members contactInfos to a new list
        val contactInformationList = filteredMembers.map { it.contact }


        for (contactInformation in contactInformationList) {
            println(contactInformation.phoneNumber)
        }

    }

    //-----------------------------  Enum & When  ------------------------------

    private fun enumAndroidTeam() {

        //Create a new array that contains only the members of the Android Development Team

        val newList = arrayListOf<AndroidBasicsMembers>()
        list.forEach { list ->
            if (list.team == EnumType.Android_Development_Team) newList.add(list)
        }

        newList.forEach { newList ->
            println(newList.fullName)
        }
    }

    private fun enumDictionary() {

        //Create a dictionary that contains the number of members in each team

        var androidCount = 0
        var iosCount = 0
        var designCount = 0
        list.forEach { list ->
            when (list.team) {
                EnumType.Android_Development_Team -> androidCount++
                EnumType.Ios_Development_Team -> iosCount++
                EnumType.UI_UX_Design_Team -> designCount++
            }
        }

        val dictionary =
            mapOf("Android" to androidCount, "Ios" to iosCount, "Design" to designCount)

        println("Number of UI/UX Team is: ${dictionary["Design"]}")
    }

    private fun teamNames(team: EnumType) {

        list.forEach { list ->
            when (list.team) {
                team -> println(list.fullName)
                else -> {}
            }
        }
    }

    private fun printInWhen() {
        list.forEach { list ->
            when (list.team) {
                EnumType.Android_Development_Team -> println("${list.fullName} is a skilled Android developer")
                EnumType.Ios_Development_Team -> println("${list.fullName} is a skilled IOS developer")
                EnumType.UI_UX_Design_Team -> println("${list.fullName} is a talented designer")
            }
        }
    }

    private fun whenAge(age: Int) {
        list.forEach { list ->
            when {
                list.age > age -> println("Members who are older than $age are: ${list.fullName} in ${list.team}")
            }
        }

    }

    private fun promoteMember() {
        list.forEach { list ->

            when (list.team) {
                EnumType.Android_Development_Team -> list.title = "Senior Android Developer"
                EnumType.Ios_Development_Team -> list.title = "Senior IOS Developer"
                EnumType.UI_UX_Design_Team -> list.title = "Lead Designer"
            }

            println("${list.fullName} is : ${list.title}")
        }
    }

    private fun averageTeamAge(team: EnumType) {

        //Create a function that takes a team as an input and calculates the average age of the members
        var teamSize = 0
        var teamTotalAge = 0
        list.forEach { list ->
            when (list.team) {
                team -> {
                    teamSize++
                    teamTotalAge += list.age
                }
                else -> {}
            }
        }
        println("Average age of the $team team is : ${teamTotalAge / teamSize}")
    }

    private fun printMessage() {
        var countTeamA = 0
        var countTeamB = 0
        var countTeamC = 0

        list.forEach { list ->
            when (list.team) {
                EnumType.Android_Development_Team -> {
                    if (countTeamA < 1) {
                        println("The Android Development Team is rising star of our academy")
                        countTeamA++
                    }
                }
                EnumType.Ios_Development_Team -> {
                    if (countTeamB < 1) {
                        println("The IOS Development Team is the the backbone of our academy")
                        countTeamB++
                    }
                }
                EnumType.UI_UX_Design_Team -> {
                    if (countTeamC < 1) {
                        println("The UI/UX Design Team is the face of our academy")
                        countTeamC++
                    }
                }
            }
        }
    }

    private fun contactInformationTeam(team: EnumType) {
        val newList = arrayListOf<AndroidBasicsMembers>()
        list.forEach { list ->
            when (list.team) {
                team -> {
                    newList.add(list)
                }
                else -> {}
            }
        }
        newList.forEach { newList ->
            println("${newList.fullName}'s info: ${newList.contact.email} and ${newList.contact.phoneNumber}")
        }
    }

    private fun titleAndAgeMessage() {
        list.forEach { list ->
            when (list.team) {
                EnumType.Android_Development_Team -> {
                    if (list.age < 25) println("${list.fullName} is a rising star as an Android developer")
                }
                EnumType.Ios_Development_Team -> {
                    if (list.age > 25) println("${list.fullName} is seasoned IOS developer")
                }
                EnumType.UI_UX_Design_Team -> {
                    if (list.age < 24) println("${list.fullName} is a rising star in design world")
                }
            }
        }
    }

    //-----------------------------  Null Safety  -------------------------------

    private fun increaseMotivation(increaseLevel: Int) {
        list.forEach { list ->
            if (list.motivationLevel == null) {
                list.motivationLevel = 1
            } else {
                list.motivationLevel = list.motivationLevel!! + increaseLevel
            }
        }
    }

    private fun motivateMembers() {
        list.forEach { list ->
            list.motivationLevel?.let {
                if (it > 5) {
                    println("This member is highly motivated")
                }
            }
            println("This member has no motivation level")
        }
    }

    private fun motivationLevelCheck(motivation: Int): String {
        list.forEach { list ->
            list.motivationLevel?.let {
                if (it >= motivation) {
                    return "highly motivated"
                }
                return "moderately motivated"
            }
        }
        return "not motivated at all"
    } // sor

    private fun returnMotivationLevel() {
        var a: Int
        list.forEach { list ->
            a = list.motivationLevel ?: 0
            println("Motivation levels of member is : $a")
        }
    }

    private fun memberAndMotivation(member: String, targetLevel: Int): Boolean {
        list.forEach { list ->
            list.motivationLevel?.let {
                if (list.fullName == member && it >= targetLevel) {
                    return true
                }
            }
        }
        return false
    }
}