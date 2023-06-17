def call() {
    pipeline {

        agent {
            label 'workstation'
        }

        stages {

            stage('compile/build') {
                steps {
                    echo 'compile'
                }
            }

            stage('unit test') {
                steps {
                    echo 'unittest'
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
}