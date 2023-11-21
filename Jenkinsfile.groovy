pipeline {
    agent { label 'ansible-master' }

    stages {
        stage('Deploy with Playbook') {
            steps {
                // Execute Ansible playbook for deployment
                sh '/usr/bin/ansible-playbook -i /home/centos/flask-project/node.ini /home/centos/flask-project/01-install-flask.yml'
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
