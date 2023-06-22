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
                    environment {
                        SONAR_USER = '$(aws ssm get-parameters --region us-east-1 --names sonarqube.user --with-decryption --query Parameters[0].Value | sed \'s/"//g\')'
                        SONAR_PASS = '$(aws ssm get-parameters --region us-east-1 --names sonarqube.pass --with-decryption --query Parameters[0].Value --with-decryption | sed \'s/"//g\')'
                    }
                    steps {

                        sh "sonar-scanner -Dsonar.host.url=http://184.73.104.208:9000 -Dsonar.login=${SONAR_USER} -Dsonar.password=${SONAR_PASS} -Dsonar.projectKey=cart"

                    }
                }
                stage('upload code to centralised place') {
                    steps {
                        echo 'upload'
                    }
                }
            }
        }
    }   catch(Exception e) {
        common.email ("failed")
    }
}