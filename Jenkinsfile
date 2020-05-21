node {

   stage('Preparation') { // for display purposes
      // Get some code from a GitHub repository
        checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, 
        extensions: [[$class: 'WipeWorkspace']], submoduleCfg: [],
        userRemoteConfigs: [[credentialsId: '497e439b-9460-4ed0-a22a-ded60c5634c4', url: 'https://github.com/InckyIncky/EPAM-Project.git']]])      // Get the Maven tool.
   }
   stage('Build') {
       
    withMaven(maven: 'maven 3.6.3') {
         // some block
         catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
            bat label: '', script: 'mvn clean test'
         }
      }
   }
   stage('Results') {
      //junit '**/target/surefire-reports/*.xml'
      
      allure jdk: '', results: [[path: 'target/allure-results']]
      
      emailext body: '''Testname: $PROJECT_NAME 
                Build number: $BUILD_NUMBER 
                Status : $BUILD_STATUS
                Git branch: $GIT_BRANCH
                Test results:
                Total: ${TEST_COUNTS,var="total"} 
                Passed: ${TEST_COUNTS,var="pass"}
                Failed: ${TEST_COUNTS,var="fail"} 
                
                Failed tests :  ${FAILED_TESTS} 
                
                                
                Check console output at $BUILD_URL to view the results.''', subject: '$DEFAULT_SUBJECT', to: 'inckyincky@yandex.ru, inckyincky049@gmail.com'
   }
}
