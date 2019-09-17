node {
        stage ('General') {
            
                echo "Current branch name: ${env.BRANCH_NAME}"
                echo "BUILD_DISPLAY_NAME: ${env.BUILD_DISPLAY_NAME}"
                echo "JOB_NAME: ${env.JOB_NAME}"
                echo "JOB_BASE_NAME: ${env.JOB_BASE_NAME}"
                //sh 'echo "artifact file" > generatedFile.txt'
                def skipBuild = getLastSuccessfulBuild()
                echo "LSB: ${skipBuild}"
            
        }        
}

/***********************
    Get artifacts from last successfull build
************************/
Boolean getLastSuccessfulBuild() {
    final String commitKey = 'COMMIT'
    final String artifactKey = 'DOWNLOAD_URL'
    final String targetCIJob = 'MultiBranchPipeline/newBranch'

    try {
        step([$class: 'CopyArtifact',
            filter: "generatedFile.txt",
            fingerprintArtifacts: true,
            flatten: true,
            selector: lastSuccessful(),
            projectName: targetCIJob])
    } catch (Exception e) {
        println "Could not find last successful build properties for job"
        println e
        return false
    }
    return true
}

