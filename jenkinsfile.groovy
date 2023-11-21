pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout code from the repository
                git branch: 'main', credentialsId: 'your-git-credentials', url: 'https://github.com/yourusername/your-flask-app.git'
            }
        }

        stage('Deploy') {
            steps {
                // Execute Ansible playbook for deployment
                sh '''
                    ansible-playbook deploy_flask_app.yml
                '''
            }
        }
    }

    post {
        success {
            echo 'Flask app deployment successful!'
        }
        failure {
            echo 'Flask app deployment failed!'
        }
    }
}
