pipeline {
    agent { label 'javaagent' }
    environment {
        PROJECT_ID = 'meghdo-4567' // Set your GCP project ID
        IMAGE_NAME = 'drizzle'     // Set the image name for GCR
        REPO_NAME = 'docker-repo'
        GCR_REGISTRY = "us-central1-docker.pkg.dev/${PROJECT_ID}/${REPO_NAME}/${IMAGE_NAME}"
        K8S_NAMESPACE = 'default'   // Set Kubernetes namespace
        HELM_RELEASE = 'drizzle' // Set Helm release name
        CHART_PATH = './helm-charts'   // Set the path to your Helm chart
        DOCKERFILE_PATH = '/workspace/Dockerfile'     // Set the path to the Dockerfile
        CONTEXT_PATH = '/workspace'                 // Set the build context for Kaniko
    }
    stages {
        stage('Maven Build') {
            steps {
                script {
                    // Execute Maven build
                    sh 'pwd'
                    sh 'mvn clean package -DskipTests'
                }
            }
        }
        stage('Kaniko Build & Push') {
            steps {
                script {
                    // Run Kaniko in a Kubernetes pod
                            sh """
                            executor --context ${CONTEXT_PATH} \
                            --dockerfile ${DOCKERFILE_PATH} \
                            --destination ${GCR_REGISTRY}:${BUILD_NUMBER} \
                            --cache=true \
                            --build-arg GIT_COMMIT=${env.GIT_COMMIT} \
                            --build-arg GIT_BRANCH=${env.GIT_BRANCH}
                            """
                 }
            }
        }
        stage('Deploy with Helm') {
            steps {
                script {
                    // Update the Helm chart with the new image tag and deploy
                    sh """
                    helm upgrade ${HELM_RELEASE} ${CHART_PATH} \
                    --namespace ${K8S_NAMESPACE} \
                    --set image.repository=${GCR_REGISTRY} \
                    --set image.tag=${BUILD_NUMBER}
                    """
                }
            }
        }
    }
}
