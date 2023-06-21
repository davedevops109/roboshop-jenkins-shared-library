def compile() {
    if (app_lang == "nodejs") {
        sh 'npm install'
        sh 'env'
    }

    if (app_lang == "maven") {
        sh 'mvn package'
    }
}

def unittests() {
    if (app_lang == "nodejs") {
        //Developer is missing the test cases in our project, we need to add them as a best practice, we are skipping to proceed further
            sh 'npm test'
    }

    if (app_lang == "maven") {
            sh 'mvn test'
    }

    if (app_lang == "python") {
            sh 'python3 -m unittest'
    }
}


def email (email_note) {
    mail bcc: '', body: "Job Failed - ${JOB_BASE_NAME}\nJenkins URL - ${JOB_URL}", cc: '', from: 'shalem.david109@gmail.com', replyTo: '', subject: "Jenkins Job failed - ${JOB_BASE_NAME}", to: 'shalem.david109@gmail.com'
}