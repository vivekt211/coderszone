<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<ul class="clearfix">
  <li><a href="${context_root}/service/home">Home</a></li>
  <li><a href="${context_root}/service/about">About</a></li>
  <c:choose>
    <c:when test="${isblogger eq true }">
      <li><a href="${context_root}/service/mypage?pageno=1&pagesize=6">MyPage</a></li>
      <li><a href="${context_root}/service/write">Write</a></li>
      <!--  <li><a href="${context_root}/service/maintainimg">Upload</a></li>-->
      <li class="dropDown"><a href="#">${user.name }</a>
        <ul>
          <li><a href="<c:url value='/service/passchange'/>">Change Password</a></li>
          <li><a href="<c:url value='/service/logout'/>">logout</a></li>
        </ul></li>
    </c:when>
    <c:otherwise>
      <li><a href="${context_root}/service/login">login</a>
    </c:otherwise>
  </c:choose>
</ul>
<!-- <ul class="clearfix">
                  <li><a href="#">Home</a>
                    <ul>
                      <li><a href="index.html">Home Default</a>
                      <li class="dropDown"><a href="#">Home - Creative</a>
                        <ul>
                          <li><a href="index-creative.html">Creative - Layout 1</a></li>
                          <li><a href="index-creative-2.html">Creative - Layout 2</a></li>
                          <li><a href="index-creative-3.html">Creative - Layout 3</a></li>
                          <li><a href="index-creative-4.html">Creative - Layout 4</a></li>
                          <li><a href="index-creative-5.html">Creative - Layout 5</a></li>
                        </ul>
                      </li>

                      <li class="dropDown"><a href="#">Home - Corporate</a>
                        <ul>
                          <li><a href="index-corporate.html">Corporate - Layout 1</a></li>
                          <li><a href="index-corporate-2.html">Corporate - Layout 2</a></li>
                          <li><a href="index-corporate-3.html">Corporate - Layout 3</a></li>
                          <li><a href="index-corporate-4.html">Corporate - Layout 4</a></li>
                        </ul>
                      </li>

                      <li class="dropDown"><a href="#">Home - Portfolio</a>
                        <ul>
                          <li><a href="index-portfolio.html">Portfolio - Layout 1</a></li>
                          <li><a href="index-portfolio-2.html">Portfolio - Layout 2</a></li>
                        </ul>
                      </li>

                      <li class="dropDown"><a href="#">Home - Blog</a>
                        <ul>
                          <li><a href="index-blog.html">Blog - Layout 1</a></li>
                          <li><a href="index-blog-2.html">Blog - Layout 2</a></li>
                          <li><a href="index-blog-3.html">Blog - Layout 3</a></li>
                        </ul>
                      </li>

                      <li class="dropDown"><a href="#">Home - FullScreen</a>
                        <ul>
                          <li><a href="index-fullscreen-image.html">FullScreen - Image</a></li>
                          <li><a href="index-fullscreen-video.html">FullScreen - Video</a></li>
                          <li><a href="index-fullscreen-slider.html">FullScreen - Slider</a></li>
                        </ul>
                      </li>

                      <li class="dropDown"><a href="#">Home - FullWidth</a>
                        <ul>
                          <li><a href="index-fullwidth-image.html">FullWidth - Image</a></li>
                          <li><a href="index-fullwidth-video.html">FullWidth - Video</a></li>
                          <li><a href="index-fullwidth-slider.html">FullWidth - Slider</a></li>
                        </ul>
                      </li>

                      <li class="dropDown"><a href="#">Home - Boxed</a>
                        <ul>
                          <li><a href="index.html">Boxed - Image</a></li>
                          <li><a href="index-video.html">Boxed - Video</a></li>
                          <li><a href="index-slider.html">Boxed - Slider</a></li>
                        </ul>
                      </li>

                    </ul>
                  </li>

                  <li class="dropDown megaMenu"><a href="#">Pages</a>
                    <div class="megaMenuContent cols3 clearfix">

                      <ul>
                        <li><a href="about.html">About Us - Layout 1</a></li>
                        <li><a href="about-2.html">About Us - Layout 2</a></li>
                        <li><a href="about-3.html">About Us - Layout 3</a></li>
                        <li><a href="about-me.html">About Me</a></li>
                        <li><a href="careers.html">Careers</a></li>
                        <li><a href="team-members.html">Team Members</a></li>
                      </ul>

                      <ul>
                        <li><a href="services.html">Services - Layout 1</a></li>
                        <li><a href="services-2.html">Services - Layout 2</a></li>
                        <li><a href="services-3.html">Services - Layout 3</a></li>
                        <li><a href="contact.html">Contact - Layout 1</a></li>
                        <li><a href="contact-2.html">Contact - Layout 2</a></li>
                        <li><a href="contact-3.html">Contact - Layout 3</a></li>
                      </ul>

                      <ul>
                        <li><a href="404.html">404 Page</a></li>
                        <li><a href="faq.html">FAQ - Layout 1</a></li>
                        <li><a href="faq-2.html">FAQ - Layout 2</a></li>
                        <li><a href="sidebar.html">Page With Sidebar</a></li>
                        <li><a href="coming-soon-video.html">Coming Soon - Video</a></li>
                        <li><a href="coming-soon-image.html">Coming Soon - Image</a></li>
                      </ul>

                    </div>

                  </li>

                  <li><a href="#">Portfolio</a>
                    <ul>
                      <li><a href="portfolio-2col-grid.html">2 Column Grid</a></li>
                      <li><a href="portfolio-2col-grid-full.html">2 Column Grid Full</a></li>
                      <li><a href="portfolio-3col-grid.html">3 Column Grid</a></li>
                      <li><a href="portfolio-3col-grid-full.html">3 Column Grid Full</a></li>
                      <li><a href="portfolio-grid-no-gutter.html">Grid Without Gutter</a></li>
                      <li><a href="portfolio-3col-masonry.html">3 Column Masonry</a></li>
                      <li><a href="portfolio-3col-masonry-full.html">3 Column Masonry Full</a></li>
                      <li><a href="portfolio-masonry-no-gutter.html">Masonry Without Gutter</a></li>
                      <li><a href="portfolio-mixed-masonry.html">Mixed Masonry</a></li>
                      <li><a href="portfolio-single-1.html">Single Page - Layout 1</a></li>
                      <li><a href="portfolio-single-2.html">Single Page - Layout 2</a></li>
                    </ul>
                  </li>

                  <li class="dropDown megaMenu">
                    <a href="#">Blog</a>
                    <div class="megaMenuContent cols3 clearfix">

                      <ul>
                        <li class="megaMenuTitle">Blog Grid Styles</li>
                        <li><a href="blog-2col-grid-white.html">2 Column Grid</a></li>
                        <li><a href="blog-3col-grid-white.html">3 Column Grid</a></li>
                        <li><a href="blog-4col-grid-black.html">4 Column Grid</a></li>
                        <li><a href="blog-grid-full.html">Full Wide Grid</a></li>
                      </ul>

                      <ul>
                        <li class="megaMenuTitle">Other Blog Styles</li>
                        <li><a href="blog-3col-masonry.html">Masonry 3 Columns</a></li>
                        <li><a href="blog-4col-masonry.html">Masonry 4 Columns</a></li>
                        <li><a href="blog-masonry-full.html">Masonry Full Wide</a></li>
                        <li><a href="blog-stacked.html">Blog Stacked</a></li>
                      </ul>

                      <ul>
                        <li class="megaMenuTitle">Single Blog Pages</li>
                        <li><a href="blog-single-1.html">Single - Layout 1</a></li>
                        <li><a href="blog-single-2.html">Single - Layout 2</a></li>
                        <li><a href="blog-single-1-sidebar.html">Single - Right Sidebar</a></li>
                        <li><a href="blog-single-2-sidebar.html">Single - Left Sidebar</a></li>
                      </ul>

                    </div>
                  </li>

                  <li><a href="#">Features</a>
                    <ul>
                      <li class="dropDown"><a href="#">Menu Styles</a>
                        <ul class="menuInvert">
                          <li><a href="menu-inline-mega.html">Inline Mega Menu</a></li>
                          <li><a href="menu-overlay.html">FullScreen Overlay</a></li>
                        </ul>
                      </li>
                      <li class="dropDown"><a href="#">Header Styles</a>
                        <ul class="menuInvert">
                          <li><a href="header-boxed-transparent.html">Transparent</a></li>
                          <li><a href="header-boxed-black.html">Black Header</a></li>
                          <li><a href="header-boxed-white.html">White Header</a></li>

                          <li class="dropDown"><a href="#">Full Width</a>
                            <ul class="menuInvert">
                              <li><a href="header-fullwidth-transparent.html">Transparent</a></li>
                              <li><a href="header-fullwidth-black.html">Black Header</a></li>
                              <li><a href="header-fullwidth-white.html">White Header</a></li>
                            </ul>
                          </li>

                          <li class="dropDown"><a href="#">Full Wide</a>
                            <ul class="menuInvert">
                              <li><a href="header-fullwide-transparent.html">Transparent</a></li>
                              <li><a href="header-fullwide-black.html">Black Header</a></li>
                              <li><a href="header-fullwide-white.html">White Header</a></li>
                            </ul>
                          </li>
                        </ul>
                      </li>
                      <li class="dropDown"><a href="#">Footer Styles</a>
                        <ul class="menuInvert">
                          <li><a href="footer-small.html">Small Footer</a></li>
                          <li><a href="footer-big.html">Big Footer 1</a></li>
                          <li><a href="footer-big-2.html">Big Footer 2</a></li>
                        </ul>
                      </li>
                    </ul>
                  </li>

                  <li class="dropDown megaMenu">
                    <a href="#">Components</a>
                    <div class="megaMenuContent cols2 clearfix">

                      <ul>
                        <li><a href="buttons.html">Buttons</a></li>
                        <li><a href="callouts-prompts.html">Callouts - Prompts</a></li>
                        <li><a href="clients.html">Clients</a></li>
                        <li><a href="columns-grid.html">Columns - Grid</a></li>
                        <li><a href="counters-skills.html">Counters - Skills</a></li>
                        <li><a href="featured-boxes.html">Featured Boxes</a></li>
                        <li><a href="icon-lists.html">Icon Lists</a></li>
                      </ul>

                      <ul>
                        <li><a href="media.html">Media &amp; Carousel</a></li>
                        <li><a href="sections.html">Page Sections</a></li>
                        <li><a href="page-titles.html">Page Titles</a></li>
                        <li><a href="pricing-tables.html">Pricing Tables</a></li>
                        <li><a href="tabs-accordions.html">Tabs - Accordions</a></li>
                        <li><a href="testimonials.html">Testimonials</a></li>
                        <li><a href="typography.html">Typography</a></li>
                      </ul>

                    </div>
                  </li>
                </ul> -->