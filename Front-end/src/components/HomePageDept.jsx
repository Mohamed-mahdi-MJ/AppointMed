import React from "react";
import { FaBriefcaseMedical } from "react-icons/fa";
import { GrFormLocation, GrLocation, GrTechnology } from "react-icons/gr";
import { MdLocalAirport, MdLocationCity, MdMyLocation, MdOutlineScience } from "react-icons/md";
import { GoLaw, GoLocation } from "react-icons/go";
import { PiCookingPotBold } from "react-icons/pi";
import { Link } from "react-router-dom";
import { motion } from "framer-motion";
import { FaMapLocation } from "react-icons/fa6";
import { GiFactory, GiHutsVillage, GiModernCity, GiVillage } from "react-icons/gi";

const cards = [
    {
        image: <MdLocationCity />,
        title: "Chennai",
        color: "bg-[#dad6b1]",
        link: "/doctor/Chennai",
    },
    {
        image: <GiFactory />,
        title: "Bangalore",
        color: "bg-[#f0cbce]",
        link: "/doctor/Bangalore",
    },
    {
        image: <GiModernCity />,
        title: "Mumbai",
        color: "bg-[#b6e6d2]",
        link: "/doctor/Mumbai"
    }
];

const HomePageDept = () => {
    return (
        <motion.section
            initial={{ opacity: 0 }}
            whileInView={{ opacity: 1 }}
            transition={{ duration: 0.8, ease: "easeInOut", delay: 0.3 }}
            viewport={{ once: true }}
            id="dept"
            className="py-24 w-full flex flex-col gap-12 lg:items-start items-center justify-center"
        >
            <h1 className="md:text-[4rem] sm:text-[3rem] ssm:text-[2.5rem] flex flex-col lg:items-start items-center font-bold">
                Choose <span className="text-center">your city</span>
            </h1>
            <div className="grid lg:grid-cols-3 md:grid-cols-2 gap-12 w-full lg:px-0 md:px-12 sm:px-8 ssm:px-4 dark:text-dark">
                {cards.map((item, index) => (
                    <Link to={`${item.link}`} key={index} className={`${item.color} py-12 flex flex-col rounded-md gap-12 items-center justify-center cursor-pointer `}>
                        <p className="text-[5rem] text-center">{item.image}</p>
                        <p className="font-medium text-xl w-[90%] text-center">{item.title}</p>
                    </Link>
                ))}
            </div>
        </motion.section>
    );
};

export default HomePageDept;
