pipeline {
    agent any

    stages {

        stage('Clone Repository') {
            steps {
                git 'https://github.com/Abinaya7247/Quiz-app.git'
            }
        }

        stage('Build') {
            steps {
                echo "Building Quiz App"
            }
        }

        stage('Test') {
            steps {
                echo "Running Tests"
            }
        }

        stage('Deploy') {
            steps {
                echo "Deploying Application"
            }
        }
    }
}
