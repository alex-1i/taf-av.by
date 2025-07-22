pipeline {
    agent any

    tools {
        maven 'Maven 3.9.6'
        jdk 'Java 21'
        allure 'Allure'
    }

    stages {
        stage('Checkout') {
            steps {
                git credentialsId: 'github-token', url: 'https://github.com/alex-1i/taf-av.by.git'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn clean test -fae'
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'

            allure([
                            results: [[path: 'allure-results']],
                            reportBuildPolicy: 'ALWAYS'
                        ])
        }
    }
}
