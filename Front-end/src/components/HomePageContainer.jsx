import React from "react";
import { lazy } from "react";

const HomePageTitle = lazy(() => import("../components/HomePageTitle.jsx"));
const HomePageDept = lazy(() => import("../components/HomePageDept"));

const HomePageContainer = () => {
    return (
        <section className="flex flex-col items-center justify-center py-8 w-full h-full xl:px-12 lg:px-8 sm:px-4 ssm:px-2">
            <HomePageTitle />
            <HomePageDept />
        </section>
    );
};

export default HomePageContainer;
