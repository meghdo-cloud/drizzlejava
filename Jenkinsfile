pipeline {
    agent { label 'javaagent' }
    environment {
        PROJECT_ID = 'meghdo-4567' // Set your GCP project ID
        CLUSTER_NAME = 'meghdo-cluster'
        CLUSTER_REGION = 'us-central1'
        IMAGE_NAME = 'drizzle'     // Set the image name for GCR
        REPO_NAME = 'docker-repo'
        GCR_REGISTRY = "us-central1-docker.pkg.dev/${PROJECT_ID}/${REPO_NAME}/${IMAGE_NAME}"
        K8S_NAMESPACE = 'default'   // Set Kubernetes namespace
        HELM_RELEASE = 'drizzle' // Set Helm release name
        CHART_PATH = './helm-charts'   // Set the path to your Helm chart
        APP_PATH = '/home/jenkins/agent/workspace/drizzle_main'
        TAG=""
    }
    stages {
        stage('Get Tag') {
             def branchName = sh(script: 'git rev-parse --abbrev-ref HEAD', returnStdout: true).trim()
             // Get first 5 characters of the commit ID
             def commitId = sh(script: 'git rev-parse --short=5 HEAD', returnStdout: true).trim()
             // Combine branch name and commit ID for the tag
             TAG = "${branchName}-${commitId}"

        }    
        stage('Maven Build') {
            
            steps {
                script {
                    // Execute Maven build
                    sh 'pwd'
                    sh 'ls -lrt'
                    sh 'mvn clean package -DskipTests'
                }
            }
        }
        stage('Kaniko Build & Push') {
            steps  {
                script {

                   container(name: 'kaniko', shell: '/busybox/sh') {
                       
                    // Run Kaniko in a Kubernetes pod

                            sh """
                            /kaniko/executor --context ${APP_PATH} \
                            --dockerfile ${APP_PATH}/Dockerfile \
                            --destination ${GCR_REGISTRY}:${TAG}
                             """
                 }
             }
          }      
        }
        stage('Deploy with Helm') {
            steps {
                script {
                    // Update the Helm chart with the new image tag and deploy
                                       
                    sh """
                    gcloud config set project $PROJECT_ID
                    gcloud container clusters get-credentials $CLUSTER_NAME --zone $CLUSTER_REGION
                    helm upgrade --install ${HELM_RELEASE} ${CHART_PATH} \
                    --namespace ${K8S_NAMESPACE} \
                    --set image.repository=${GCR_REGISTRY} \
                    --set image.tag=${TAG}
                    """
                }
            }
        }
    }
}
