// Comment to get more information during initialization
logLevel := Level.Warn

// Improve incremental compilation by 25% to 80%
//incOptions := incOptions.value.withNameHashing(true)

// Improve dependency management
//updateOptions := updateOptions.value.withCachedResolution(true)

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.4.6")

// web plugins

addSbtPlugin("com.typesafe.sbt" % "sbt-less" % "1.1.1")

addSbtPlugin("com.typesafe.sbt" % "sbt-jshint" % "1.0.3")

addSbtPlugin("com.typesafe.sbt" % "sbt-digest" % "1.1.0")

// Safe Scala Plugins

addSbtPlugin("org.brianmckenna" % "sbt-wartremover" % "0.13")

addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.8.0")

addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.1.8")

addSbtPlugin("com.typesafe.sbt" % "sbt-play-enhancer" % "1.1.0")

// Plugin to Deploy to AWS

addSbtPlugin("com.github.tptodorov" % "sbt-cloudformation" % "0.5.0")

addSbtPlugin("com.gilt" % "sbt-codedeploy" % "0.4.0")