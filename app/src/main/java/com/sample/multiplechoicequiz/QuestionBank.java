package com.sample.multiplechoicequiz;

// This file contains questions from QuestionBank

class QuestionBank {

    // array of questions
    private String textQuestions [] = {
            "When did Google acquire Android?",
            "What is the name of the build toolkit for Android Studio?",
            "What widget can replace any use of radio buttons?",
            "Which principle does Android application implement?",
            "What does ADT stand for?",
            "Which notification is unavailable in Android?",
            "What is ANR?",
            "What are shared preferences used for?",
            "Where are the layouts placed in Android?",
            "How to switch to another activity?",
            "Which kernel is used in Android?"
    };

    // array of multiple choices for each question
    private String multipleChoice [][] = {
            {"2003", "2004", "2005", "2006"},
            {"JVM", "Gradle", "Dalvik", "HAXM"},
            {"Toggle Button", "Spinner", "Switch Button", "ImageButton"},
            {"least privileges", "most privileges", "unique privileges", "NOTA"},
            {"Android Development Tool", "Adreno Deployment Tool", "Amber Dota Technique", "After Development Tank"},
            {"Toast", "Status Bar", "Error", "Dialogue"},
            {"Android Nudge Request", "App Not Responding", "Android Number Request", "Application News Response"},
            {"To store data in XML", "To share settings", "To sync preferences", "NOTA"},
            {"HTML", "Java", "XML", "Layout"},
            {"Intent", "Broadcast", "Message passing", "Layout shift"},
            {"Linux 3.6", "Dalvik 23", "X 2.5", "Linux 2.3"},
    };

    // array of correct answers - in the same order as array of questions
    private String mCorrectAnswers[] = {"2005", "Gradle", "Spinner", "least privileges", "Android Development Tool", "Error", "App Not Responding", "To store data in XML", "XML", "Intent", "Linux 3.6" };

    // method returns number of questions
    int getLength(){return textQuestions.length;}


    // method returns question from array textQuestions[] based on array index
    String getQuestion(int a) {
        return textQuestions[a];
    }

    // method return a single multiple choice item for question based on array index,
    // based on number of multiple choice item in the list - 1, 2, 3 or 4 as an argument
    String getChoice(int index, int num) {
        return multipleChoice[index][num];
    }

    //  method returns correct answer for the question based on array index
    String getCorrectAnswer(int a) {
        return mCorrectAnswers[a];
    }
}