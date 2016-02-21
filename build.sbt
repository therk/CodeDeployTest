name := """play-scala-intro"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))

scalaVersion := "2.11.7"


enablePlugins(PlayScala, SbtWeb, CodeDeployPlugin)

scalacOptions ++= Seq(
  "-language:postfixOps",
  "-feature",
  "-unchecked"
)

resolvers += "Kaliber Internal Repository" at "https://jars.kaliber.io/artifactory/libs-release-local"
resolvers += Resolver.url("Typesafe Ivy", url("http://repo.typesafe.com/typesafe/ivy-snapshots"))(Resolver.ivyStylePatterns)

val awsSdkVersion = "1.10.54"

libraryDependencies ++= Seq(
  cache,
  ws,
  evolutions,
  "com.typesafe.play"         % "play_2.11"               % "2.4.6",
  "com.typesafe.slick"        %% "slick"                  % "3.1.1",
  "com.typesafe.play"         %% "play-slick"             % "1.1.1",
  "com.typesafe.play"         %% "play-slick-evolutions"  % "1.1.1",
  "org.slf4j"                 % "slf4j-nop"               % "1.7.7",
  "org.mindrot"               % "jbcrypt"                 % "0.3m",
  "joda-time"                 % "joda-time"               % "2.9.2",
  "org.joda"                  % "joda-convert"            % "1.7",
  "com.github.tototoshi"      %% "slick-joda-mapper"      % "2.1.0",
  "com.typesafe.play"         %% "play-mailer"            % "4.0.0-M1",
  "io.strongtyped"            %% "active-slick"           % "0.3.3",
  "net.kaliber"               %% "play-s3"                % "7.0.2",
  "mysql"                     % "mysql-connector-java"    % "5.1.38",
  "org.apache.pdfbox"         % "pdfbox"                  % "1.8.11",
  "com.opencsv"               % "opencsv"                 % "3.7",
  "be.objectify"              %% "deadbolt-scala"         % "2.4.3",
  "com.amazonaws"             % "aws-java-sdk-codedeploy" % awsSdkVersion,
  "com.amazonaws"             % "aws-java-sdk-s3"         % awsSdkVersion,
  "org.scalatest"             %% "scalatest"              % "2.2.5"    % "test",
  "org.scalatestplus"         %% "play"                   % "1.4.0-M3" % "test",
  "org.scalacheck"            %% "scalacheck"             % "1.12.4"   % "test",
  "com.h2database"            % "h2"                      % "1.4.191"
)

// to remove the warning "project contains multiple SLF4J bindings"
libraryDependencies ~= { _.map(_.exclude("org.slf4j", "slf4j-nop")) }

scalacOptions ++= Seq(
  // Emit warning for usages of deprecated APIs
  "-deprecation"
  // Emit warning for usages of features that should be imported explicitly
  , "-feature"
  // Enable additional warnings where generated code depends on assumptions
  , "-unchecked"
  // Fail the compilation if there are any warnings
  , "-Xfatal-warnings"
  // Enable warnings, but ignore missing interpolator warnings.
  // This is to ignore expected 'routes' warnings
  , "-Xlint:-missing-interpolator,_"
  // Do not adapt an argument list to match the receiver
  , "-Yno-adapted-args"
  // Warn when dead code is identified
  , "-Ywarn-dead-code"
  // Warn when local and private vals, vars, defs, and types are are unused
  , "-Ywarn-unused"
  // Warn when imports are unused, but it is a bit difficult to fix for Play template
  // But first need to fix Play templates imports, which may take a bit of time
  //, "-Ywarn-unused-import"
  // Warn when non-Unit expression results are unused
  , "-Ywarn-value-discard"
)

testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-u", "target/test-reports")

scalacOptions in Test ++= Seq("-Yrangepos")

name in CodeDeploy := "sbt-codedeploy-play-example"
codedeployRegion := com.amazonaws.regions.Regions.US_EAST_1
codedeployBucket := "backoffice-codedeploy-oregon"

codedeployPermissionMappings := Seq(
  sbt.codedeploy.PermissionMapping(
    objectPath = "bin/simple-example",
    mode = "0755",
    owner = "root",
    group = "root"
  )
)

fork in run := true