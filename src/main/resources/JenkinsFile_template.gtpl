pipeline {
    agent any
    environment {
        branch = <%= pipeline.branch %>
        scmUrl = <%= pipeline.scmUrl %>
        serverPort = <%= pipeline.serverPort %>
        developmentServer = <%= pipeline.developmentServer %>
        stagingServer = <%= pipeline.stagingServer %>
        productionServer = <%= pipeline.productionServer %>
    }
    stages {
        stage('checkout git') {
            steps {
                git branch: branch, credentialsId: <%= pipeline.GitCredentials}, url: scmUrl
            }
        }

        stage('build') {
            steps {
                sh 'mvn clean package -DskipTests=true'
            }
        }

        stage ('test') {
            steps {
                parallel (
                    "unit tests": { sh 'mvn test' },
                    "integration tests": { sh 'mvn integration-test' }
                )
            }
        }

        stage('deploy development'){
            steps {
                deploy(developmentServer, serverPort)
            }
        }

        stage('deploy staging'){
            steps {
                deploy(stagingServer, serverPort)
            }
        }

        stage('deploy production'){
            steps {
                deploy(productionServer, serverPort)
            }
        }
    }
    post {
        failure {
            mail to: 'team@example.com', subject: 'Pipeline failed', body: <%= pipeline.productionServer %>
        }
    }
}