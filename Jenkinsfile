library identifier: 'javaPipeline@main', retriever: modernSCM([$class: 'GitSCMSource',
    remote: 'git@github.com:meghdo-cloud/shared-libraries.git',
    credentialsId: 'git'])
javaPipeline ([
    projectId = 'meghdo-4567',
    clusterName = 'meghdo-cluster',
    clusterRegion = 'us-central1',
    appName =  'drizzle',
    dockerRegistry = 'us-central1-docker.pkg.dev',
    namespace = 'default'
])
