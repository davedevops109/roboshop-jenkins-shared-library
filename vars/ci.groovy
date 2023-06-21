def call() {
    try {
        pipeline {

            agent {
                label 'workstation'
            }

            stages {

                stage('compile/build') {
                    steps {
                        script {
                            common.compile()
                        }
                    }
                }

                stage('unit tests') {
                    steps {
                        script {
                            common.unittests()
                        }
                    }
                }
                stage('Quality control') {
                    steps {
                        echo 'compile'
                    }
                }
                stage('upload code to centralised place') {
                    steps {
                        echo 'upload'
                    }
                }
            }
        }
    }   catch (Exception e) {
        common.email ("failed")
    }
}