pipeline {
    agent 'ansible-master'

    stages {

        stage('Deploy with Playbook') {
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
