node {
        stage ('General') {
            
                echo "Current branch name: ${env.BRANCH_NAME}"
                echo "BUILD_DISPLAY_NAME: ${env.BUILD_DISPLAY_NAME}"
                echo "JOB_NAME: ${env.JOB_NAME}"
                echo "JOB_BASE_NAME: ${env.JOB_BASE_NAME}"
                //sh 'echo "artifact file" > generatedFile.txt'                
                
                def skipBuild = getLastSuccessfulBuild()
                echo "LSB: ${skipBuild}"
                archiveArtifacts artifacts: 'generatedFile.txt', fingerprint: true
        }  

        stage ('test1') {
            echo "Testing stage1"
            echo "Hello...."
        }

        stage ('test2') {
            echo "Testing stage2"
            echo "Hello...."
        }      
}

/***********************
    Get artifacts from last successfull build
************************/
Boolean getLastSuccessfulBuild() {
    final String commitKey = 'COMMIT'
    final String artifactKey = 'DOWNLOAD_URL'
    final String targetCIJob = 'MultiBranchPipeline/PR-2'

    try {
        step([$class: 'CopyArtifact',
            filter: "generatedFile.txt",
            fingerprintArtifacts: true,
            flatten: true,
            selector: lastWithArtifacts(),
            projectName: targetCIJob])
    } catch (Exception e) {
        println "Could not find last successful build properties for job"
        println e
        return false
    }
    return true
}

