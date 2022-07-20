pipeline {

    agent {
        node("test-executor")
    }

    parameters {
        string(name: 'branch', defaultValue: 'master', description: 'Branch to checkout')
        string(name: 'SUITE', defaultValue: 'src/test/resources/methods_suite.xml',
            description: '''TestNG xml suite. Examples:
                            src/test/resources/methods_suite.xml,
                            src/test/resources/methods_suite.xml,src/test/resources/first_suite.xml.
                            Leave this field empty if you want to run single test class.
                            ''')
        string(name: 'TEST_CLASS', defaultValue: 'tests.HomePageTests',
            description: '''Select test class to execute. Examples:
                            tests.HomePageTests,
                            tests.AdvancedSelenium.
                            Leave this field empty if you want to run xml suite.
                            ''')
        string(name: 'forks', defaultValue: '1', description: 'Number of parallel threads')
    }

    stages {

        stage('Execute tests'){
            steps {
                script {
                    if ( !SUITE.isEmpty() ) {
                        bat "mvn clean test -Dsurefire.suiteXmlFiles=${SUITE} -Dforks=${params.forks}"
                    }
                    if ( !TEST_CLASS.isEmpty() ) {
                        bat "mvn clean -Dtest=${TEST_CLASS} test -Dforks=${params.forks}"
                    }
                }
            }
        }
    }

}