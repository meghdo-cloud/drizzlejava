library identifier: 'javaPipeline@main', retriever: modernSCM([$class: 'GitSCMSource',
    remote: 'git@github.com:meghdo-cloud/shared-libraries.git',
    credentialsId: 'jenkins_agent_ssh'])
javaPipeline ([
    projectId = config.projectId,
    clusterName = config.clusterName,
    clusterRegion = config.clusterRegion,
    appName =  config.appName,
    dockerRegistry = config.dockerRegistry,
    namespace = config.namespace
])
