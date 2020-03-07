if (process.env.NODE_ENV === "production") {
    const opt = require("./slinky-demo-opt.js");
    opt.main();
    module.exports = opt;
} else {
    var exports = window;
    exports.require = require("./slinky-demo-fastopt-entrypoint.js").require;
    window.global = window;

    const fastOpt = require("./slinky-demo-fastopt.js");
    fastOpt.main()
    module.exports = fastOpt;

    if (module.hot) {
        module.hot.accept();
    }
}
