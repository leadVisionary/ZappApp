// JavaScript Document

$(function() {
    $.superbox();
});

$.superbox.settings = {
    boxId: "superbox",
    // Id attribute of the "superbox" element
    boxClasses: "",
    // Class of the "superbox" element
    overlayOpacity: .8,
    // Background opaqueness
    boxWidth: "696",
    // Default width of the box
    boxHeight: "478"
    // Default height of the box
    };

$(document).ready(function() {

    $("#features .featurepanel, #plans tr.hover, .contactinfo div div").hover(function() {

//Change the color of the table rows as the mouse hovers

        $(this).stop().animate({ 
            backgroundColor: "#e5e5e5"
        }, "fast");
    }, function() {

        $(this).stop().animate({
            backgroundColor: "#F0F0F0"
        }, "slow");
    });

//Make the heights of the feature panel and the individual testimonies equal

    $("#testimonials div div").equalHeights();

    $("#backgrounds img").hide(); //Hide backgrounds before being loaded

    $("#spanel").click(function() { //This whole block of the javascript is explained in the help file.
        return false;
    });

    $(".sl1").click(function() {

        $("#spanelbg").animate({
            left: "4px"
        }, 150, 'easeInCirc');
        $(".slider").animate({
            right: "0px"
        }, 450, 'easeInCubic');
        $("#slider li").fadeOut("fast");
        $("#slider li.sp1").fadeIn("slow");
        $("#backgrounds img").fadeOut(950);
        return false;
    });

    $(".sl2").click(function() {

        $("#spanelbg").animate({
            left: "86px"
        }, 250, 'easeInCirc');
        $(".slider").animate({
            right: "220px"
        }, 450, 'easeInCubic');
        $("#slider li").fadeOut("fast");
        $("#slider li.sp2").fadeIn("slow");
        $("#backgrounds img").fadeOut("fast");
        $("#backgrounds #bg1").fadeIn(950);
        return false;
    });

    $(".sl3").click(function() {

        $("#spanelbg").animate({
            left: "170px"
        }, 250, 'easeInCirc');
        $(".slider").animate({
            right: "440px"
        }, 450, 'easeInCubic');
        $("#slider li").fadeOut("fast");
        $("#slider li.sp3").fadeIn("slow");
        $("#backgrounds img").fadeOut("fast");
        $("#backgrounds #bg2").fadeIn(950);
        return false;
    });

    $(".sl4").click(function() {

        $("#spanelbg").animate({
            left: "259px"
        }, 250, 'easeInCirc');
        $(".slider").animate({
            right: "660px"
        }, 450, 'easeInCubic');
        $("#slider li").fadeOut("fast");
        $("#slider li.sp4").fadeIn("slow");
        $("#backgrounds img").fadeOut("fast");
        $("#backgrounds #bg3").fadeIn(950);
        return false;
    });

    $("#testimonials").cycle({ //Cycles the testimonials. Feel free to check the help file for more info. 
        timeout: 8000
    });

});