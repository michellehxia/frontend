package model

import conf.Static

sealed trait EmailContent extends Product with Serializable {
  def name: String
  def banner: String
  def test(c: ContentType): Boolean
}

case object ArtWeekly extends EmailContent {
  val name = "Art Weekly"
  val banner = "art-weekly.png"
  def test(c: ContentType) = c.tags.series.exists(_.id == "artanddesign/series/art-weekly")
}

case object GreenLight extends EmailContent {
  val name = "Green Light"
  val banner = "green-light.png"
  def test(c: ContentType) = c.tags.series.exists(_.id == "environment/series/green-light")
}

case object MoneyTalks extends EmailContent {
  val name = "Money Talks"
  val banner = "money-talks.png"
  def test(c: ContentType) = c.tags.series.exists(_.id == "money/series/money-talks")
}

case object PovertyMatters extends EmailContent {
  val name = "Poverty Matters"
  val banner = "poverty-matters.png"
  def test(c: ContentType) = c.tags.blogs.exists(_.id == "global-development/poverty-matters")
}

case object TheBreakdown extends EmailContent {
  val name = "The Breakdown"
  val banner = "the-breakdown.png"
  def test(c: ContentType) = c.tags.series.exists(_.id == "sport/series/breakdown")
}

case object TheFiver extends EmailContent {
  val name = "The Fiver"
  val banner = "the-fiver.png"
  def test(c: ContentType) = c.tags.series.exists(_.id == "football/series/thefiver")
}

case object TheSpin extends EmailContent {
  val name = "The Spin"
  val banner = "the-spin.png"
  def test(c: ContentType) = c.tags.series.exists(_.id == "sport/series/thespin")
}

object EmailAddons {
  private val defaultBanner = "generic.png"
  private val allEmails     = Seq(ArtWeekly, GreenLight, MoneyTalks, PovertyMatters, TheBreakdown, TheFiver, TheSpin)

  implicit class EmailContentType(c: ContentType) {
    val email = allEmails.find(_.test(c))

    lazy val banner = {
      val banner = email map (_.banner) getOrElse defaultBanner
      Static(s"images/email/banners/$banner").path
    }
  }
}