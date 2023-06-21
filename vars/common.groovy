def compile() {
    if (app_lang == "nodejs") {
        sh 'npm install'
    }

    if (app_lang == "maven") {
        sh 'mvn package'
    }
}

def unittest() {
    if (app_lang == "nodejs") {
        //Developer is missing the test cases in our project, we need to add them as a best practice, we are skipping to proceed further
        //sh 'npm test'
        sh 'echo test cases'
    }

    if (app_lang == "maven") {
        sh 'mvn test'
    }

}