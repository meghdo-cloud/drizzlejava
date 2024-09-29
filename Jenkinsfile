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
        TAG="${GIT_BRANCH}-${GIT_COMMIT[0..5]}"
    }
    stages {
        stage('SCM Skip') {
            steps {
                script {
                    skipStages = false
                    scmSkip = sh(script: 'git log -1 --pretty=%B ${GIT_COMMIT}', returnStdout: true).trim()
                    if (scmSkip.contains("[ci skip]")) {
                        skipStages = true
                        currentBuild.description = "SCM Skip - Build skipped as no new commits in branch"
                    }
                    echo Boolean.toString(skipStages)
                }
            }    
        }   
        stage('Maven Build') {    
            when {
                expression { return !skipStages }
            }    
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
            when {
                expression { return !skipStages }
            }              
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
            when {
                expression { return !skipStages }
            }              
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
    post {
        always {
            script {
                cleanWs()
            }
        }  
    }            
}
