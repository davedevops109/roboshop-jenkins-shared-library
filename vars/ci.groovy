def call() {
    try {
        node ('workstation') {

            stage('CleanUp') {
                cleanWs()
            }
            stage('compile/build') {
                common.compile()
            }

            stage ('Unit Tests') {
                common.unittests()
            }

            stage ('Quality control') {
                SONAR_PASS = sh ( script: 'aws ssm get-parameters --region us-east-1 --names sonarqube.pass --with-decryption --query Parameters[0].Value | sed \'s/"//g\'', returnStdout: true).trim()
                SONAR_USER = '$(aws ssm get-parameters --region us-east-1 --names sonarqube.user --with-decryption --query Parameters[0].Value | sed \'s/"//g\')'
                wrap([$class: 'MaskPasswordsBuildWrapper', varPasswordPairs: [[password: "${SONAR_PASS}", var: 'SECRET']]]) {
                    sh "sonar-scanner -Dsonar.host.url=http://52.6.208.71:9000/ -Dsonar.login=${SONAR_USER} -Dsonar.password=${SONAR_PASS} -Dsonar.projectKey=cart"
                }
            }
            stage('upload code to centralised place') {
                echo 'upload'
            }
        }


    }   catch(Exception e) {
        common.email ("failed")
    }
}