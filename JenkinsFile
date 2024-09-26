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
        DOCKERFILE_PATH = 'Dockerfile'     // Set the path to the Dockerfile
        CONTEXT_PATH = '.'                 // Set the build context for Kaniko
        GIT_REPO = 'git@github.com:meghdo-cloud/drizzle.git'
    }
    stages {
        stage('Checkout Code') {
            steps {
                script {
                    // Checkout the code from GitHub using SSH
                    checkout([
                        $class: 'GitSCM',
                        branches: [[name: '*/main']],  // Adjust for your branch
                        userRemoteConfigs: [[url: "${GIT_REPO}", credentialsId: '']],

                        // Specify the SSH configuration
                        doGenerateSubmoduleConfigurations: false,
                        submoduleCfg: [],
                        extensions: [
                            [$class: 'CloneOption', noTags: false, shallow: true, depth: 1]
                        ]
                    ])
                }
            }
        }
        stage('Maven Build') {
            steps {
                script {
                    // Execute Maven build
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
