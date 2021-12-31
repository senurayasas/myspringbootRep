@Library(['github.com/releaseworks/jenkinslib', 'jenkinslib@master']) _
pipeline {
    environment {
        SERVICE_NAME = "${artifactId}"
    }

    agent {
        docker {
            image 'gifted-agent:latest'
            registryUrl 'https://130994068316.dkr.ecr.eu-west-1.amazonaws.com/gifted-agent'
            registryCredentialsId 'ecr:eu-west-1:Jenkins-AWS-ECR'
            args '-u 0 -v /maven_cache/repository:/root/.m2/repository -v /var/run:/var/run -v /usr/bin/docker:/usr/bin/docker'
            reuseNode true
        }
    }

    stages {

        stage('Setup') {
            steps {
                notifyBuild('STARTED')
            }
        }

        stage('Test') {
            when { not { changeRequest() } }
            steps {
                runTests()
            }
        }

        stage('Sonar') {
            when { changeRequest() }
            steps {
                runSonar()
            }
        }

        stage('Build and publish docker image') {
            when { expression { env.BRANCH_NAME == 'develop' || env.BRANCH_NAME.contains('release/') || env.BRANCH_NAME == 'master' } }
            steps {
                buildAndPushDocker("${env.SERVICE_NAME}")
            }
        }

        stage('Deploy to gg-ecs-dev') {
            when { expression { env.BRANCH_NAME == 'develop' } }
            steps {
                ecsDeploy("${env.SERVICE_NAME}", "dev", getSha())
            }
        }
    }

    post {
        success {
            notifyBuild('SUCCESS')
        }
        failure {
            notifyBuild('FAILED')
        }
    }
}