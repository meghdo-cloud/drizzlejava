library identifier: 'javaPipeline@main', retriever: modernSCM([$class: 'GitSCMSource',
    remote: 'git@github.com:meghdo-cloud/shared-libraries.git',
    credentialsId: 'jenkins_agent_ssh'])
javaPipeline (
    projectId: "meghdo-4567",
    clusterName: "meghdo-cluster",
    clusterRegion: "europe-west1",
    appName: "drizzle",
    dockerRegistry: "europe-west1-docker.pkg.dev",
    namespace: "default"
)
