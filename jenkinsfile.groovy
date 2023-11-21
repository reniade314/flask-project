pipeline {
    agent 'ansible-master'

    stages {
        stage('Checkout') {
            steps {
                // Checkout code from the repository
                git branch: 'main', credentialsId: '3e7c1305-8065-4201-b132-d794720a01e1', url: 'https://github.com/reniade314/flask-project.git'
            }
        }

        stage('Deploy') {
            steps {
                // Execute Ansible playbook for deployment
                ansiblePlaybook(
                    credentialsId: 'flask-1',
                    disableHostKeyChecking: true,
                    installation: 'Ansible',
                    inventory: '/home/centos/flask-project/node.ini',
                    playbook: '/home/centos/flask-project/01-install-flask.yml',
                    vaultTmpPath: ''
                )
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
