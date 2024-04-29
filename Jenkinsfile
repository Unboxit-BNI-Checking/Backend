pipeline {
    agent any

    environment {
        db_password = credentials('bf887856-38ad-4410-93b7-b5121f0094b6')
        docker_login_password = credentials('b6d657b1-feea-4ff0-91f6-f71c9602f0cc')
    }

    stages {
        stage('Delete the old container') {
            steps {
                echo 'Deleting old container'
                sh 'docker rm -f unboxit-mono-api'
            }
        }

        stage('Delete unused images') {
            steps {
                echo 'Deleting unused images'
                sh 'docker image prune -a -f'
            }
        }

        stage('Login docker using unboxit account') {
            steps {
                echo 'Pulling the image'
                sh "docker login ghcr.io -u jonathanrichard13 -p '${docker_login_password.toString()}'"
            }
        }

        stage('Pull the latest image') {
            steps {
                echo 'Pulling the image'
                sh 'docker pull ghcr.io/unboxit-bni-checking/backend:latest'
            }
        }

        stage('Run the container on port 1110') {
            steps {
                echo 'Running the container'
                sh "echo '${db_password.toString()}' && docker run -d --restart unless-stopped --name unboxit-mono-api -p 1200:8080 ghcr.io/unboxit-bni-checking/backend:latest --spring.application.name=bni-checking --security.ignored=/** --spring.jpa.hibernate.ddl-auto=none --spring.datasource.url=jdbc:postgresql://direct.50soa.my.id:5432/bni_checking --spring.datasource.username=unboxit-dbuser --spring.datasource.password='${db_password.toString()}' --spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect --seed.data.enabled=true --seed.data.csv.path=data/account.csv --account.id=1 --spring.servlet.multipart.max-file-size=10MB --spring.servlet.multipart.max-request-size=10MB --spring.servlet.multipart.location=ReportAttachments" 
            }
        }

        stage('Check whether the container is running or not') {
            steps {
                echo 'Checking the container'
                sh 'docker ps'
            }
        }
    }
}