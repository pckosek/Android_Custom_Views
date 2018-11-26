% SAMPLE RATE STUFF
FS = 44100;

% ENVELOPE STUFF
A.time = .05;
A.amp  = 1;

D.time  = .1;
D.amp   = .7;

S.time = .3;
S.amp  = .1;

R.time = .2;
R.amp  = 0;
env = [...
    linspace(0,A.amp, FS*A.time),...
    linspace(A.amp,D.amp, FS*D.time),...
    linspace(D.amp,S.amp, FS*S.time),...
    linspace(S.amp,R.amp, FS*R.time)];

num_samps = size(env,2);

% MUSIC STUFF
base_freq = 27.5;
growth_factor = 2^(1/12);

C_MAJOR = [0;4;7]*ones(1,3) + 39 + [0, 12, 24];C_MAJOR = C_MAJOR(:)';
G_MAJOR = [0;4;7]*ones(1,3) + 34 + [0, 12, 24];G_MAJOR = G_MAJOR(:)';
A_MINOR = [0;3;7]*ones(1,3) + 36 + [0, 12, 24];A_MINOR = A_MINOR(:)';
F_MAJOR = [0;4;7]*ones(1,3) + 32 + [0, 12, 24];F_MAJOR = F_MAJOR(:)';

c.chord = C_MAJOR; c.prefix = 'cmaj';
g.chord = G_MAJOR; g.prefix = 'gmaj';
a.chord = A_MINOR; a.prefix = 'amin';
f.chord = F_MAJOR; f.prefix = 'fmin';

for chords = { c, g, a, f }
    
    NOTES  = chords{1}.chord;
    PREFIX = chords{1}.prefix;
    for indx = 1:length(NOTES)
        freq = base_freq*(growth_factor^NOTES(indx));
        f1 = cos(2*pi*freq*[1:num_samps]./FS);
        wav = f1.*env; 
        wav = .5*wav/max(abs(wav));

        f_name = sprintf('%s_%i.mp4',PREFIX, indx);
        audiowrite(f_name, wav, 44100 );
    end
end
