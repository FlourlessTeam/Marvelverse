package com.example.marvelverse.data.dataSources.local

import com.example.marvelverse.domain.entities.About
import javax.inject.Inject

class FakeLocalData @Inject constructor(){

    fun getAboutItems(): List<About>{
        return listOf(About(HISTORY1), About(HISTORY2), About(HISTORY3), About(HISTORY4))
    }

    companion object{
        const val HISTORY1 = "Marvel is a platform that allows you to create and prototype mobile and web applications. It provides a range of tools and features that enable you to design, collaborate, and share your designs with stakeholders and clients. With Marvel, you can easily create interactive prototypes that simulate the user experience of your app, allowing you to test and refine your design before development."
        const val HISTORY2 = "The Marvel Comics API allows developers everywhere to access information about Marvel's vast library of comics—from what's coming up, to 70 years ago. "
        const val HISTORY3 = "Subject to these API Terms of Use (defined below), Marvel Digital Media Group LLC (\\\"Marvel\\\") may make available to you certain data and content (collectively, \\\"Content\\\") through the Marvel API, as well as related tools and utilities (collectively, \\\"Tools\\\") for your use to develop free (as further described and limited below) applications"
        const val HISTORY4 = "Marvel may amend these API Terms of Use (including by modification, deletion and/or addition of any portion thereof) at any time. We will post a notice of any material amendment on our available APIs page. Any amendment to these API Terms of Use will be effective thirty (30) calendar days following the posting date, so please review these API Terms of Use periodically to ensure that you and your Apps comply with any applicable amendments."
    }
}