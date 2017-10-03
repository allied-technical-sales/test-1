var Transform = require('stream').Transform,
    util = require('util'),
    combine = require('stream-combiner'),
    map = require('map-stream'),
    split = require('split');

function HeadStream(opts) {
    Transform.call(this);
    this.head = opts.head || 0;
    this.tail = opts.tail || 0;
    this.message = '...';

    if (opts.message || opts.message === null) {
        this.message = opts.message;
    }

    // if the message is not an array, convert it to one
    if (this.message !== null && typeof this.message !== 'object') {
        this.message = [this.message];
    }

    this.messageShown = false;
    this.lineNum = 0;
    this.tailWindow = [];
}

util.inherits(HeadStream, Transform);

HeadStream.prototype._transform = function(data, encoding, callback) {
    this.lineNum++;

    // one of the first lines in the head range
    if (this.head && this.lineNum <= this.head) {
        this.push(data);

        // if there are no more lines to capture, signal the end of
        // the stream to avoid further processing
        if (this.lineNum == this.head && !this.tail) {
            this.push(null);
        }

        callback();
        return;
    }

    // show all the message lines
    if (this.head && this.tail && this.message !== null && !this.messageShown) {
        var _this = this;
        this.message.forEach(function(msgLine) {
            _this.push(msgLine);
        });
        this.messageShown = true;
    }

    // keep the last lines that might be the tail to show when the stream ends
    if (data && data.toString() && this.tail) {
        if (this.tailWindow.length >= this.tail) {
            this.tailWindow.shift();
        }

        this.tailWindow.push(data);
    }

    callback();
};

HeadStream.prototype._flush = function(callback) {
    var _this = this;

    // flush the tail lines when the stream has ended
    this.tailWindow.forEach(function(data) {
        _this.push(data);
    });

    callback();
};

module.exports = function(head, tail) {
    var opts = {};

    // opts was provided as first arg
    if (typeof head === 'object') {
        opts = head;
    }
    // head and tail were provided as numbers
    else {
        if (head >= 0) {
            opts.head = head;
            opts.tail = tail;
        }
        // there's only a tail, provided as negative first arg
        else {
            opts.head = 0;
            opts.tail = -head;
        }
    }

    // stream to restore the newline character
    var newLine = opts.newLine || '\n';
    var mapNewline = map(function(line, callback) {
        callback(null, line+newLine);
    });

    // unless the option 'lineStream' is used to specify that the
    // input stream is already a line-separated stream, run it
    // through split() first to ensure the input received is
    // splitted by lines
    if (opts.lineStream) {
        return new HeadStream(opts);
    } else {
        return combine(split(), new HeadStream(opts), mapNewline);
    }
};
